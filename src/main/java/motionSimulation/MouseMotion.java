package motionSimulation;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * 通过jni控制鼠标
 */
public class MouseMotion {
    public interface Mouse extends Library {
        Mouse INSTANCE = (Mouse) Native.loadLibrary("lib/Mouse", Mouse.class);
        public void mouseUp();
        public void mouseDown();
        public void mouseLeft();
        public void mouseRight();
        public void mouseMoveTo(int a, int b);
        public void mouseMoveBy(int a, int b);
        public void mouseLeftDown();
        public void mouseLeftUp();
        public void mouseRightDown();
        public void mouseRightUp();
        public void mouseMiddleDown();
        public void mouseMiddleUp();
        public void mouseWheelRotate(int a);
    }

    public static void up(){
        Mouse.INSTANCE.mouseUp();
    }
    public static void down(){
        Mouse.INSTANCE.mouseDown();
    }
    public static void left(){
        Mouse.INSTANCE.mouseLeft();
    }
    public static void right(){
        Mouse.INSTANCE.mouseRight();
    }
    public static void moveTo(int horizontal,int vertical){
        Mouse.INSTANCE.mouseMoveTo(horizontal,vertical);
    }
    public static void moveBy(int horizontal,int vertical){
        Mouse.INSTANCE.mouseMoveBy(horizontal,vertical);
    }
    public static void leftDown(){
        Mouse.INSTANCE.mouseLeftDown();
    }
    public static void leftUp(){
        Mouse.INSTANCE.mouseLeftUp();
    }
    public static void rightDown(){
        Mouse.INSTANCE.mouseRightDown();
    }
    public static void rightUp(){
        Mouse.INSTANCE.mouseRightUp();
    }
    public static void middleDown(){
        Mouse.INSTANCE.mouseMiddleDown();
    }
    public static void middleUp(){
        Mouse.INSTANCE.mouseMiddleUp();
    }
    public static void wheelRotate(int distance){
        Mouse.INSTANCE.mouseWheelRotate(distance);
    }
}
