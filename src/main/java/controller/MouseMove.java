package controller;

public class MouseMove {
    static native boolean up();
    static native boolean down();
    static native boolean left();
    static native boolean right();
    static native boolean moveTo(int horizontal,int vertical);
    static native boolean moveBy(int horizontal,int vertical);
    static {
        System.loadLibrary("MouseMove");
    }
}
