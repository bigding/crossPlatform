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

import java.util.logging.Level;
import java.util.logging.Logger;

public class GlobalDeviceListener implements NativeMouseInputListener, NativeKeyListener, NativeMouseWheelListener {

//    public  ActionContainer actionContainer = null;
    public  ActionContainer actionContainer = new ActionContainer();

    public void nativeMouseClicked(NativeMouseEvent e) {
//        System.out.println("MouseMove Clicked: " + e.getClickCount());
        JSONObject mouseClick = new JSONObject();
        mouseClick.put("id","1");
        mouseClick.put("clickCount",e.getClickCount());
        try {
            actionContainer.offer(mouseClick);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public void nativeMousePressed(NativeMouseEvent e) {
//        System.out.println("MouseMove Pressed: " + e.getButton());
        JSONObject mousePress = new JSONObject();
        mousePress.put("id","2");
        mousePress.put("press",e.getButton());
        try {
            actionContainer.offer(mousePress);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public void nativeMouseReleased(NativeMouseEvent e) {
//        System.out.println("MouseMove Released: " + e.getButton());
        JSONObject mouseRelease = new JSONObject();
        mouseRelease.put("id","3");
        mouseRelease.put("release",e.getButton());
        try {
            actionContainer.offer(mouseRelease);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public void nativeMouseMoved(NativeMouseEvent e) {
//        System.out.println("MouseMove Moved: " + e.getX() + ", " + e.getY());
        JSONObject mouseMove = new JSONObject();
        mouseMove.put("id","4");
        mouseMove.put("posiX",e.getX());
        mouseMove.put("posiY",e.getY());
//        System.out.println(mouseMove);
        try {
            actionContainer.offer(mouseMove);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public void nativeMouseDragged(NativeMouseEvent e) {
//        System.out.println("MouseMove Dragged: " + e.getX() + ", " + e.getY());
        JSONObject mouseDrag = new JSONObject();
        mouseDrag.put("id","5");
        mouseDrag.put("posiX",e.getX());
        mouseDrag.put("posiY",e.getY());
        try {
            actionContainer.offer(mouseDrag);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
//        System.out.println("MouseMove Wheel Moved: " + e.getWheelRotation());
        JSONObject mouseWhellMove = new JSONObject();
        mouseWhellMove.put("id","6");
        mouseWhellMove.put("wheelRotation",e.getWheelRotation());
        try {
            actionContainer.offer(mouseWhellMove);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
//        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        JSONObject keyPress = new JSONObject();
        keyPress.put("id","7");
        keyPress.put("keyText",NativeKeyEvent.getKeyText(e.getKeyCode()));
        keyPress.put("keyCode",e.getKeyCode());
        try {
            actionContainer.offer(keyPress);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }


    public void nativeKeyReleased(NativeKeyEvent e) {
//        System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        JSONObject keyRelease = new JSONObject();
        keyRelease.put("id","8");
        keyRelease.put("keyText",NativeKeyEvent.getKeyText(e.getKeyCode()));
        keyRelease.put("keyCode",e.getKeyCode());
        try {
            actionContainer.offer(keyRelease);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
//        System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        JSONObject keyType = new JSONObject();
        keyType.put("id","9");
        keyType.put("keyText",NativeKeyEvent.getKeyText(e.getKeyCode()));
        keyType.put("keyCode",e.getKeyCode());
        try {
            actionContainer.offer(keyType);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public void startGlobalListener() {
//        actionContainer = ActionContainer.getActionContainer();
//        actionContainer = new ActionContainer();

        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);
        logger.setUseParentHandlers(false);

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        // Construct the example object.
        GlobalDeviceListener globalDeviceListener = new GlobalDeviceListener();

        // Add the appropriate listeners.
        GlobalScreen.addNativeMouseListener(globalDeviceListener);
        GlobalScreen.addNativeMouseMotionListener(globalDeviceListener);
        GlobalScreen.addNativeKeyListener(globalDeviceListener);
        GlobalScreen.addNativeMouseWheelListener(globalDeviceListener);
    }
}