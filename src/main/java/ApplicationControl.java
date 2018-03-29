import common.ActionContainer;
import listener.GlobalDeviceListener;
import net.Client;
import net.Server;
import org.apache.log4j.Logger;

/**
 *  程序启动启动入口
 */
public class ApplicationControl {
//    private static Logger logger =  Logger.getLogger(ApplicationControl.class);
    public static void main(String[] args) throws InterruptedException {
        ActionContainer serverActionContainer = new ActionContainer();   //服务器端设备的动作信息 以及控制信息
        ActionContainer clientActionContainer = new ActionContainer();   //客户端设备的操作信息
        Server server = Server.getServer();                              //单例模式获取Server
        Client client =Client.getClient();                               //单例模式获取Client

        GlobalDeviceListener globalDeviceListener = new GlobalDeviceListener();
        globalDeviceListener.setActionContainer(serverActionContainer);

        server.startServer(serverActionContainer);
        client.startClient(clientActionContainer);
        globalDeviceListener.startGlobalListener();
    }
}
