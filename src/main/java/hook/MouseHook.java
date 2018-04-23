package hook;

import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.MSG;


/**
 * 鼠标钩子
 */
public class MouseHook {
    //鼠标事件编码
    public static final int WM_MOUSEMOVE = 512;
    public static final int WM_LBUTTONDOWN = 513;
    public static final int WM_LBUTTONUP = 514;
    public static final int WM_RBUTTONDOWN = 516;
    public static final int WM_RBUTTONUP = 517;
    public static final int WM_MBUTTONDOWN = 519;
    public static final int WM_MBUTTONUP = 520;
    public static final int WM_WHEELMOVE = 522;

    private static volatile boolean quit;  //是否退出hook的标志位
    final User32 lib = User32.INSTANCE;
    MouseHookListener hookListener;
    private static HHOOK hhk;
    private HMODULE hMod;


    public void startMouseHook() {
//        try {
            hMod = Kernel32.INSTANCE.GetModuleHandle(null);
            hookListener = new MouseHookListener() {
                //回调监听
                public LRESULT callback(int nCode, WPARAM wParam, MouseHookStruct lParam) {
                    lib = User32.INSTANCE;
                    long flag = 1;
                    if (nCode >= 0) {
                        switch (wParam.intValue()) {
                            case MouseHook.WM_MOUSEMOVE:
//                                System.err.println("mouse move, x=" + lParam.pt.x + " y=" + lParam.pt.y);
                                break;
                            case MouseHook.WM_LBUTTONDOWN:
                                System.out.println("left  down");
                                break;
                            case MouseHook.WM_LBUTTONUP:
                                System.out.println("left  up");
                                break;
                            case MouseHook.WM_RBUTTONDOWN:
                                System.out.println("right down");
                                break;
                            case MouseHook.WM_RBUTTONUP:
                                System.out.println("right up");
                                break;
                            case MouseHook.WM_MBUTTONDOWN:
                                System.out.println("middle down");
                                break;
                            case MouseHook.WM_MBUTTONUP:
                                System.out.println("middle up");
                                break;
                            case MouseHook.WM_WHEELMOVE:
                                boolean down = Pointer.nativeValue(lParam.hwnd.getPointer()) == 4287102976L;
                                if (down)
                                    System.out.println("down");
                                else System.out.println("up");

                                //return new LRESULT(flag);  //关闭鼠标滚轮滚动功能
                                break;
                            default:
                                System.out.println("Invalid operation");
                                break;
                        }
                    }
                    //将钩子信息传递到当前钩子链中的下一个子程，一个钩子程序可以调用这个函数之前或之后处理钩子信息
                    //hhk：当前钩子的句柄
                    //nCode ：钩子代码; 就是给下一个钩子要交待的，钩传递给当前Hook过程的代码。下一个钩子程序使用此代码，以确定如何处理钩的信息。
                    //wParam：要传递的参数; 由钩子类型决定是什么参数，此参数的含义取决于当前的钩链与钩的类型。
                    //lParam：Param的值传递给当前Hook过程。此参数的含义取决于当前的钩链与钩的类型。
                    return lib.CallNextHookEx(hhk, nCode, wParam, lParam.getPointer());
                }
            };
            hhk = lib.SetWindowsHookEx(WinUser.WH_MOUSE_LL, hookListener, hMod, 0);

            new Thread() {
                @Override
                public void run() {
                    while (!quit) {
                        try {
                            Thread.sleep(10);
                        } catch (Exception e) {
                        }
                    }
                    lib.UnhookWindowsHookEx(hhk);
                    System.exit(0);
                }
            }.start();

            int result;
            MSG msg = new MSG();
            while ((result = lib.GetMessage(msg, null, 0, 0)) != 0) {
                if (result == -1) {
                    System.err.println("err in get message");
                    break;
                } else {
                    System.err.println("got message");
                    lib.TranslateMessage(msg);
                    lib.DispatchMessage(msg);
                }
            }
            lib.UnhookWindowsHookEx(hhk);
//        }
//        catch (Exception e){
//
//        }
    }


    public void stopMouseHook() {
        quit = true;
    }
}

