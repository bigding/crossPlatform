import com.sun.jna.Library;
import com.sun.jna.Native;
import motionSimulation.DisableMotion;

public class HookTest {
    public interface  Hook extends Library{
        Hook INSTANCE = (Hook) Native.loadLibrary("lib/Hook",Hook.class);
        public void FuncEndHook();
        public void FuncHookDevice();
    }

    public static void main(String[] args) {
//        Hook.INSTANCE.FuncHookDevice();
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Hook.INSTANCE.FuncEndHook();
        WinEventInterceptor.setKeyDisable();
    }
}
