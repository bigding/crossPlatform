import common.ActionContainer;
import hook.HookControl;

public class HookTest {
    static ActionContainer actionContainer = new ActionContainer();

    public static void main(String[] args) throws InterruptedException {
        HookControl hookControl = new HookControl(actionContainer);
        hookControl.startHook();

        hookControl.enableInput();

    }
}