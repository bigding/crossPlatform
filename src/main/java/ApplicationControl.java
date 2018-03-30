import common.ActionContainer;
import net.Client;
import net.Server;

/**
 *  程序启动启动入口
 */
public class ApplicationControl {
//    private static Logger logger =  Logger.getLogger(ApplicationControl.class);

    /**
     * 使用命令行参数来启动项目
     * server端: java * 1 port/ java * 1(使用默认的端口)
     * client端: java * 2 ip port/java * 2(使用默认的ip和端口)
     * @param args
     */
    public static void main(String[] args) {
        if("1".equals(args[0])){
            ActionContainer serverActionContainer = new ActionContainer();   //服务器端设备的动作信息 以及控制信息

            if(args.length == 1){
                Server server = Server.getServer();                              //单例模式获取Server
                server.startServer(serverActionContainer);
            }
            else if(args.length == 2){
                try {
                    Server server = Server.getServer(Integer.valueOf(args[1]));
                    server.startServer(serverActionContainer);                           //单例模式获取Server
                }
                catch (NumberFormatException e){
                    System.out.println("invalid input parameter.");
                }
            }
        }
        else if("2".equals(args[0])){
            ActionContainer clientActionContainer = new ActionContainer();   //客户端设备的操作信息
            if(args.length == 1){
                Client client =Client.getClient();                               //单例模式获取Client
                client.startClient(clientActionContainer);
            }
            else if(args.length == 3){
                try {
                    Client client = Client.getClient(args[1],Integer.valueOf(args[2]));                               //单例模式获取Client
                    client.startClient(clientActionContainer);
                }catch (NumberFormatException e){
                    System.out.println("invalid input parameter.");
                }
            }
        }
        else{
            System.out.println("invalid input parameter.");
        }
    }
}
