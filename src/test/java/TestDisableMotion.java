//import motionSimulation.DisableMotion;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

public class TestDisableMotion {
    public interface CLibrary extends Library {
        CLibrary INSTANCE = (CLibrary)Native.loadLibrary("lib/Hook", CLibrary.class);

        void FuncHookDevice();
        void FuncEndHook();
    }
    public static void main(String[] args) {
        CLibrary.INSTANCE.FuncHookDevice();
    }
}
