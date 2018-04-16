package motionSimulation;

public class DisableMotion {
    public static native void disableAll();
    public static native void enableAll();

    static {
        System.loadLibrary("lib/x64/DisableMotion");
    }
}
