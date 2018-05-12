package hook;

import com.alibaba.fastjson.JSONObject;
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
import common.ActionContainer;
import org.apache.log4j.Logger;
import util.Key;

/** Sample implementation of a low-level keyboard hook on W32. */
public class KeyHook {

    private  static Logger log4j = Logger.getLogger(KeyHook.class);

    private static volatile boolean quit;  //是否退出hook的标志位
    private static volatile boolean isHook = false;   //is disable input
    private static HHOOK hhk;
    private static LowLevelKeyboardProc keyboardHook;
    final User32 lib = User32.INSTANCE;
    HMODULE hMod;

    ActionContainer actionContainer = new ActionContainer();

    public KeyHook(){}
    public KeyHook(ActionContainer container){
        actionContainer = container;
    }


    public void startKeyHook(){
        log4j.info("start key hook.");
        hMod = Kernel32.INSTANCE.GetModuleHandle(null);
        keyboardHook = new LowLevelKeyboardProc() {
            @Override
            public LRESULT callback(int nCode, WPARAM wParam, KBDLLHOOKSTRUCT info) {
                System.out.println("in key");
//                lib = User32.INSTANCE;
                long flag = 1;
                if (nCode >= 0) {
                    switch(wParam.intValue()) {
                        case WinUser.WM_KEYUP:
                            if(isHook) {
                                JSONObject keyRelease = new JSONObject();
                                keyRelease.put("id", "8");
//            keyRelease.put("keyText", NativeKeyEvent.getKeyText(e.getKeyCode()));
                                keyRelease.put("keyCode", info.vkCode);  //  jnativehook 各个key的编码和键盘ascii码不同,需要转换一下
                                //按q退出 调试小后门
//                            if(info.vkCode == 81){
//                                quit = true;
//                            }
                                try {
                                    actionContainer.offer(keyRelease);
                                } catch (InterruptedException e1) {
                                    log4j.error("add keyReleased event to eventContainer fail");
                                }
                            }
                            break;
                        case WinUser.WM_KEYDOWN:
                            if(isHook) {
                                JSONObject keyPress = new JSONObject();
                                keyPress.put("id", "7");
                                keyPress.put("keyCode", info.vkCode);
                                try {
                                    actionContainer.offer(keyPress);
                                } catch (InterruptedException e1) {
                                    log4j.error("add keyPressed event to eventContainer fail");
                                }
                            }
                            break;
                        case WinUser.WM_SYSKEYUP:
//                            System.out.println("sysKeyUp, key=" + info.vkCode);
                            break;
                        case WinUser.WM_SYSKEYDOWN:
//                            System.out.println("sysKeuDown, key=" + info.vkCode);
                            break;
                    }
                }

                if(!isHook) {
                    Pointer ptr = info.getPointer();
                    long peer = Pointer.nativeValue(ptr);
                    return lib.CallNextHookEx(hhk, nCode, wParam, new LPARAM(peer));
                }
                else
                    return new LRESULT(flag);
            }
        };
        hhk = lib.SetWindowsHookEx(WinUser.WH_KEYBOARD_LL, keyboardHook, hMod, 0);


        new Thread() {
            @Override
            public void run() {
                while (!quit) {
                    try { Thread.sleep(10); } catch(Exception e) { }
                }
                log4j.info("exit key hook.");
                lib.UnhookWindowsHookEx(hhk);
            }
        }.start();

        // This bit never returns from GetMessage
        boolean result;
        MSG msg = new MSG();
        while (!quit) {
            while ((result = lib.PeekMessage(msg, null, 0, 0, 1)) != false) {
                if (result == true) {
                    log4j.error("error when get key message.");
                    break;
                } else {
                    log4j.info("get key message.");
                    lib.TranslateMessage(msg);
                    lib.DispatchMessage(msg);
                }
            }
        }
    }

    public void stopKeyHook(){
        quit = true;
    }


    public void enableInput(){
        isHook = false;
    }

    public void disableInput(){
        isHook = true;
    }
}