package controller;

public class KeyboardMotion {
    public static native void keyDown(int key);
    public static native void keyUp(int key);

    static {
        System.loadLibrary("KeyboardMotion");
    }
}
