package motionSimulation;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class DisableMotion {
    public interface  Hook extends Library {
        Hook INSTANCE = (Hook) Native.loadLibrary("lib/x64/Hook",Hook.class);
        public void FuncEndHook();
        public void FuncHookDevice();
    }
    public static void enableDevice(){
        Hook.INSTANCE.FuncEndHook();
    }
    public static void disableDevice(){
        Hook.INSTANCE.FuncHookDevice();
    }
}
