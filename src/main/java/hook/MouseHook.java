package hook;

import com.alibaba.fastjson.JSONObject;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.MSG;
import common.ActionContainer;
import org.apache.log4j.Logger;


/**
 * 鼠标钩子
 */
public class MouseHook {

    private static Logger log4j = Logger.getLogger(MouseHook.class);

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

    ActionContainer actionContainer;


    public MouseHook(){}
    public MouseHook(ActionContainer container){
        actionContainer = container;
    }


    public void startMouseHook() {
        log4j.info("start mouse hook");
//        try {
            hMod = Kernel32.INSTANCE.GetModuleHandle(null);
            hookListener = new MouseHookListener() {
                //回调监听
                public LRESULT callback(int nCode, WPARAM wParam, MouseHookStruct lParam) {
                    System.out.println("in mouse");
                    lib = User32.INSTANCE;
                    long flag = 1;
                    if (nCode >= 0) {
                        switch (wParam.intValue()) {
                            case MouseHook.WM_MOUSEMOVE:
//                                System.err.println("mouse move, x=" + lParam.pt.x + " y=" + lParam.pt.y);
//                                return new LRESULT(flag);
                                break;
                            case MouseHook.WM_LBUTTONDOWN:
                                JSONObject lMousePress = new JSONObject();
                                lMousePress.put("id", "2");
                                lMousePress.put("press", 1);
                                try {
                                   actionContainer.offer(lMousePress);
                                } catch (InterruptedException e1) {
                                    log4j.error("add left mousePressed event to eventContainer fail");
                                }
                                return new LRESULT(flag);
//                                break;
                            case MouseHook.WM_LBUTTONUP:
                                JSONObject lMouseRelease = new JSONObject();
                                lMouseRelease.put("id", "3");
                                lMouseRelease.put("release", 1);
                                try {
//                log4j.info("mouseRelease:" + mouseRelease);
                                    actionContainer.offer(lMouseRelease);
                                } catch (InterruptedException e1) {
                                    log4j.error("add left mouseReleased event to eventContainer fail");
                                }
                                return new LRESULT(flag);
//                                break;
                            case MouseHook.WM_RBUTTONDOWN:
                                JSONObject rMousePress = new JSONObject();
                                rMousePress.put("id", "2");
                                rMousePress.put("press", 2);
                                try {
                                    actionContainer.offer(rMousePress);
                                } catch (InterruptedException e1) {
                                    log4j.error("add right mousePressed event to eventContainer fail");
                                }
                                return new LRESULT(flag);
                            case MouseHook.WM_RBUTTONUP:
                                JSONObject rMouseRelease = new JSONObject();
                                rMouseRelease.put("id", "3");
                                rMouseRelease.put("release", 2);
                                try {
                                   actionContainer.offer(rMouseRelease);
                                } catch (InterruptedException e1) {
                                    log4j.error("add right mouseReleased event to eventContainer fail");
                                }
                                return new LRESULT(flag);
                            case MouseHook.WM_MBUTTONDOWN:
                                JSONObject mMousePress = new JSONObject();
                                mMousePress.put("id", "2");
                                mMousePress.put("press", 3);
                                try {
                                    actionContainer.offer(mMousePress);
                                } catch (InterruptedException e1) {
                                    log4j.error("add middle mousePressed event to eventContainer fail");
                                }
                                return new LRESULT(flag);
                            case MouseHook.WM_MBUTTONUP:
                                JSONObject mMouseRelease = new JSONObject();
                                mMouseRelease.put("id", "3");
                                mMouseRelease.put("release", 3);
                                try {
                                    actionContainer.offer(mMouseRelease);
                                } catch (InterruptedException e1) {
                                    log4j.error("add middle mouseReleased event to eventContainer fail");
                                }
                                return new LRESULT(flag);
                            case MouseHook.WM_WHEELMOVE:
                                boolean down = Pointer.nativeValue(lParam.hwnd.getPointer()) == 4287102976L;
                                JSONObject mouseWheelMove = new JSONObject();
                                mouseWheelMove.put("id", "6");
                                if (down){
                                    mouseWheelMove.put("wheelRotation", -1);
                                }
                                else{
                                    mouseWheelMove.put("wheelRotation", 1);
                                }
                                try {
                                    actionContainer.offer(mouseWheelMove);
                                } catch (InterruptedException e1) {
                                    log4j.error("add wheel move event to eventContainer fail");
                                }

                                return new LRESULT(flag);  //关闭鼠标滚轮滚动功能
//                                break;
                            default:
                                log4j.info("mouse hook:invalid operation");
                                break;
                        }
                    }
                    //将钩子信息传递到当前钩子链中的下一个子程，一个钩子程序可以调用这个函数之前或之后处理钩子信息
                    //hhk：当前钩子的句柄
                    //nCode ：钩子代码; 就是给下一个要交待的，钩传递给当前Hook过程的代码。下一个钩子程序使用此代码，以确定如何处理钩的信息。
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
                    log4j.info("exit mouse hook.");
                    lib.UnhookWindowsHookEx(hhk);
                }
            }.start();

            int result;
            MSG msg = new MSG();
            while ((result = lib.GetMessage(msg, null, 0, 0)) != 0) {
                if (result == -1) {
                    System.err.println("error in get message");
                    break;
                } else {
                    System.err.println("got message");
                    lib.TranslateMessage(msg);
                    lib.DispatchMessage(msg);
                }
            }
//            lib.UnhookWindowsHookEx(hhk);
//        }
//        catch (Exception e){
//
//        }
    }


    public void stopMouseHook() {
        quit = true;
    }
}

