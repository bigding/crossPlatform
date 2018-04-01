package listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import common.ActionContainer;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;
import util.Key;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * github 的开源项目 kwhat/jnativehook
 * 将上面项目中的四个监听器集中起来,并统一管理
 */
public class GlobalDeviceListener implements NativeMouseInputListener, NativeKeyListener, NativeMouseWheelListener {

    private static org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(GlobalDeviceListener.class);

    public static  ActionContainer actionContainer;
//    public  ActionContainer actionContainer = new ActionContainer();
    //注册全体监听器
    static GlobalDeviceListener globalDeviceListener = new GlobalDeviceListener();

    public void setActionContainer(ActionContainer actionContainer){
        this.actionContainer = actionContainer;
    }

    public void nativeMouseClicked(NativeMouseEvent e) {
        JSONObject mouseClick = new JSONObject();
        mouseClick.put("id","1");
        mouseClick.put("clickCount",e.getClickCount());
        try {
            this.actionContainer.offer(mouseClick);
        } catch (InterruptedException e1) {
            log4j.error("add mouseChecked event to eventContainer fail");
        }
    }

    public void nativeMousePressed(NativeMouseEvent e) {
        JSONObject mousePress = new JSONObject();
        mousePress.put("id","2");
        mousePress.put("press",e.getButton());
        try {
            this.actionContainer.offer(mousePress);
        } catch (InterruptedException e1) {
            log4j.error("add mousePressed event to eventContainer fail");
        }
    }

    public void nativeMouseReleased(NativeMouseEvent e) {
        JSONObject mouseRelease = new JSONObject();
        mouseRelease.put("id","3");
        mouseRelease.put("release",e.getButton());
        try {
            this.actionContainer.offer(mouseRelease);
        } catch (InterruptedException e1) {
            log4j.error("add mouseReleased event to eventContainer fail");
        }
    }

    public void nativeMouseMoved(NativeMouseEvent e) {
        JSONObject mouseMove = new JSONObject();
        mouseMove.put("id","4");
        mouseMove.put("posiX",e.getX());
        mouseMove.put("posiY",e.getY());
        try {
            this.actionContainer.offer(mouseMove);
        } catch (InterruptedException e1) {
            log4j.error("add mouseMoved event to eventContainer fail");
        }
    }

    public void nativeMouseDragged(NativeMouseEvent e) {
        JSONObject mouseDrag = new JSONObject();
        mouseDrag.put("id","5");
        mouseDrag.put("posiX",e.getX());
        mouseDrag.put("posiY",e.getY());
        try {
            this.actionContainer.offer(mouseDrag);
        } catch (InterruptedException e1) {
            log4j.error("add mouseDragged event to eventContainer fail");
        }
    }

    public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
        JSONObject mouseWhellMove = new JSONObject();
        mouseWhellMove.put("id","6");
        mouseWhellMove.put("wheelRotation",e.getWheelRotation());
        try {
            this.actionContainer.offer(mouseWhellMove);
        } catch (InterruptedException e1) {
            log4j.error("add mouseWheelMoved event to eventContainer fail");
        }
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        int keyCode = Key.getKeyText(e.getKeyCode());
        if(keyCode != -1) {
            JSONObject keyPress = new JSONObject();
            keyPress.put("id", "7");
//            keyPress.put("keyText", NativeKeyEvent.getKeyText(e.getKeyCode()));
            keyPress.put("keyCode", keyCode);  //  jnativehook 各个key的编码和键盘ascii码不同,需要转换一下
            try {
                this.actionContainer.offer(keyPress);
            } catch (InterruptedException e1) {
                log4j.error("add keyPressed event to eventContainer fail");
            }
        }
    }


    public void nativeKeyReleased(NativeKeyEvent e) {
        int keyCode = Key.getKeyText(e.getKeyCode());
        if(keyCode != -1) {
            JSONObject keyRelease = new JSONObject();
            keyRelease.put("id", "8");
//            keyRelease.put("keyText", NativeKeyEvent.getKeyText(e.getKeyCode()));
            keyRelease.put("keyCode", keyCode);  //  jnativehook 各个key的编码和键盘ascii码不同,需要转换一下
            try {
                this.actionContainer.offer(keyRelease);
            } catch (InterruptedException e1) {
                log4j.error("add keyReleased event to eventContainer fail");
            }
        }
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        int keyCode = Key.getKeyText(e.getKeyCode());
        if(keyCode != -1) {
            JSONObject keyType = new JSONObject();
            keyType.put("id", "9");
//            keyType.put("keyText", NativeKeyEvent.getKeyText(e.getKeyCode()));
            keyType.put("keyCode", keyCode);  //  jnativehook 各个key的编码和键盘ascii码不同,需要转换一下
            try {
                this.actionContainer.offer(keyType);
            } catch (InterruptedException e1) {
                log4j.error("add keyTyped event to eventContainer fail");
            }
        }
    }

    /**
     * 开启监听器
     */
    public void startGlobalListener(ActionContainer serverActionContainer) {

        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);
        logger.setUseParentHandlers(false);

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            log4j.error("There was a problem registering the native hook.");
            log4j.error(ex.getMessage());

            System.exit(1);
        }
        //当没有注册时候就开始监听
        this.setActionContainer(serverActionContainer);

        GlobalScreen.addNativeMouseListener(globalDeviceListener);
        GlobalScreen.addNativeMouseMotionListener(globalDeviceListener);
        GlobalScreen.addNativeKeyListener(globalDeviceListener);
        GlobalScreen.addNativeMouseWheelListener(globalDeviceListener);
    }

    /**
     * 关闭监听器
     */
    public void removeGlobalListener() {
        GlobalScreen.removeNativeMouseWheelListener(globalDeviceListener);
        GlobalScreen.removeNativeKeyListener(globalDeviceListener);
        GlobalScreen.removeNativeMouseMotionListener(globalDeviceListener);
        GlobalScreen.removeNativeMouseListener(globalDeviceListener);
    }
}