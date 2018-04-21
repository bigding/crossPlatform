import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.KBDLLHOOKSTRUCT;
import com.sun.jna.platform.win32.WinUser.LowLevelKeyboardProc;
import com.sun.jna.platform.win32.WinUser.MSG;

/** Sample implementation of a low-level keyboard hook on W32. */
public class KeyListenHook {
    private static volatile boolean quit;
    private static HHOOK hhk;
    private static LowLevelKeyboardProc keyboardHook;

    public static void main(String[] args) {
        final User32 lib = User32.INSTANCE;
        HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);
        keyboardHook = new LowLevelKeyboardProc() {
            @Override
            public LRESULT callback(int nCode, WPARAM wParam, KBDLLHOOKSTRUCT info) {
                long flag = 1;
                if (nCode >= 0) {
                    switch(wParam.intValue()) {
                        case WinUser.WM_KEYUP:
                            System.err.println("keyUp, key=" + info.vkCode);
                            break;
                        case WinUser.WM_KEYDOWN:
                            System.err.println("keyDown, key=" + info.vkCode);
                            if (info.vkCode == 81) {
                                quit = true;
                            }
                            break;
                        case WinUser.WM_SYSKEYUP:
                            System.err.println("sysKeyUp, key=" + info.vkCode);
                            break;
                        case WinUser.WM_SYSKEYDOWN:
                            System.err.println("sysKeuDown, key=" + info.vkCode);
                            break;
                    }
                }

                Pointer ptr = info.getPointer();
                long peer = Pointer.nativeValue(ptr);
                //return lib.CallNextHookEx(hhk, nCode, wParam, new LPARAM(peer));

                return new LRESULT(flag);
            }
        };
        hhk = lib.SetWindowsHookEx(WinUser.WH_KEYBOARD_LL, keyboardHook, hMod, 0);
        System.out.println("Keyboard hook installed, type anywhere, 'q' to quit");
        new Thread() {
            @Override
            public void run() {
                while (!quit) {
                    try { Thread.sleep(10); } catch(Exception e) { }
                }
                System.err.println("unhook and exit");
                lib.UnhookWindowsHookEx(hhk);
                System.exit(0);
            }
        }.start();

        // This bit never returns from GetMessage
        int result;
        MSG msg = new MSG();
        while ((result = lib.GetMessage(msg, null, 0, 0)) != 0) {
            if (result == -1) {
                System.err.println("error in get message");
                break;
            }
            else {
                System.err.println("got message");
                lib.TranslateMessage(msg);
                lib.DispatchMessage(msg);
            }
        }
        lib.UnhookWindowsHookEx(hhk);
    }
}