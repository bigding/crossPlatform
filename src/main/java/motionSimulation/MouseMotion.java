package motionSimulation;

/**
 * 通过jni控制鼠标
 */
public class MouseMotion {
    public static native void up();
    public static native void down();
    public static native void left();
    public static native void right();
    public static native void moveTo(int horizontal,int vertical);
    public static native void moveBy(int horizontal,int vertical);
    public static native void leftDown();
    public static native void leftUp();
    public static native void rightDown();
    public static native void rightUp();
    public static native void middleDown();
    public static native void middleUp();
    public static native void wheelRotate(int distance);

    static {
        System.loadLibrary("lib/MouseMotion");
    }
}
