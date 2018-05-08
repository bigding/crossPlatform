import com.sun.jna.Library;
import com.sun.jna.Native;
import common.ActionContainer;
import hook.HookControl;

public class HookTest {
    public static ActionContainer serverActionContainer = new ActionContainer();

    public static void main(String[] args) throws InterruptedException {
        HookControl hookControl = new HookControl(serverActionContainer);
        Thread th = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("1");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        th.start();
        new Thread() {
            @Override
            public void run() {
                hookControl.startHook();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hookControl.stopHook();
            }
        }.start();
    }
}
