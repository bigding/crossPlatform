package motionSimulation;

/**
 * 通过jni控制键盘事件
 */
public class KeyboardMotion {
    public static native void keyDown(int key);
    public static native void keyUp(int key);

    static {
        System.loadLibrary("lib/KeyboardMotion");
    }
}
