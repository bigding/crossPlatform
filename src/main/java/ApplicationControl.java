import listener.ActionListener;
import listener.GlobalDeviceListener;

public class ApplicationControl {
    public static void main(String[] args) throws InterruptedException {
        ActionListener actionListener = new ActionListener();
        GlobalDeviceListener globalDeviceListener = new GlobalDeviceListener();

        new Thread(actionListener).start();
        globalDeviceListener.startGlobalListener();
    }
}
