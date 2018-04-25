package listener;

import com.alibaba.fastjson.JSONObject;
import common.ActionContainer;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * github 的开源项目 kwhat/jnativehook
 * 将上面项目中的四个监听器集中起来,并统一管理
 */
public class GlobalMouseListener implements NativeMouseInputListener{

    private static org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(GlobalMouseListener.class);


    public static  ActionContainer mouseMoveContainer;
//    public  ActionContainer actionContainer = new ActionContainer();
    //注册全体监听器
    static GlobalMouseListener globalMouseListener = new GlobalMouseListener();

    public void setActionContainer(ActionContainer mouseMoveContainer){
        this.mouseMoveContainer = mouseMoveContainer;
    }



    public void nativeMouseMoved(NativeMouseEvent e) {
        JSONObject mouseMove = new JSONObject();
        mouseMove.put("id","4");
        mouseMove.put("posiX",e.getX());
        mouseMove.put("posiY",e.getY());
//        log4j.info(mouseMove);
        try {
            this.mouseMoveContainer.offer(mouseMove);
        } catch (InterruptedException e1) {
            log4j.error("add mouseMoved event to eventContainer fail");
        }
    }



    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent nativeMouseEvent) {

    }

    /**
     * 开启监听器
     */
    public void startGlobalListener(ActionContainer motionContainer) {

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
        
        this.setActionContainer(motionContainer);

        GlobalScreen.addNativeMouseListener(globalMouseListener);
        GlobalScreen.addNativeMouseMotionListener(globalMouseListener);
    }

    /**
     * 关闭监听器
     */
    public void removeGlobalListener() {
        GlobalScreen.removeNativeMouseMotionListener(globalMouseListener);
        GlobalScreen.removeNativeMouseListener(globalMouseListener);
    }

}