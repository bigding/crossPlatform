import com.sun.jna.Library;
import com.sun.jna.Native;
import motionSimulation.DisableMotion;

public class HookTest {
    public interface  Hook extends Library{
        Hook INSTANCE = (Hook) Native.loadLibrary("lib/x64/Hook",Hook.class);
        public void FuncEndHook();
        public void FuncHookDevice();
    }

    public static void main(String[] args) throws InterruptedException {
        Hook.INSTANCE.FuncHookDevice();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println(i);
        }
        Hook.INSTANCE.FuncEndHook();
//        WinEventInterceptor.setKeyDisable();
    }
}
