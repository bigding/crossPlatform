import listener.ActionListener;
import listener.GlobalDeviceListener;

/**
 *  程序启动启动入口
 */
public class ApplicationControl {
    public static void main(String[] args) throws InterruptedException {
        ActionListener actionListener = new ActionListener();
        GlobalDeviceListener globalDeviceListener = new GlobalDeviceListener();

        new Thread(actionListener).start();
        globalDeviceListener.startGlobalListener();
    }
}
