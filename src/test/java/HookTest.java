import com.sun.jna.Library;
import com.sun.jna.Native;
import common.ActionContainer;
import hook.HookControl;

public class HookTest {
    public static ActionContainer serverActionContainer = new ActionContainer();

    public static void main(String[] args) throws InterruptedException {
        HookControl hookControl = new HookControl(serverActionContainer);
        hookControl.startHook();
        Thread.sleep(5000);
        hookControl.stopHook();
        System.out.println("aa:"+hookControl.isStop());
        Thread.sleep(5000);
        System.out.println("bb:"+hookControl.isStop());
        System.out.println("once again");
        hookControl.startHook();
        Thread.sleep(5000);
        hookControl.stopHook();
        System.out.println("cc:"+hookControl.isStop());
    }
}