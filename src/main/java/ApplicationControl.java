import common.ActionContainer;
import common.SystemInfo;
import net.Client;
import net.Server;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  程序启动启动入口
 */
public class ApplicationControl {
//    private static Logger logger =  Logger.getLogger(ApplicationControl.class);

    /**
     * 使用命令行参数来启动项目
     * @param args
     * server端: java * 1 port/ java * 1(使用默认的端口)
     * client端: java * 2 ip port/java * 2(使用默认的ip和端口)
     */
    public static void main(String[] args) {
        if("1".equals(args[0])){
            ActionContainer motionContainer = new ActionContainer(); //用于存储主设备动作的信息
            ActionContainer serverActionContainer = new ActionContainer();   //服务器端设备的动作信息 以及控制信息
            ConcurrentHashMap<String, SystemInfo> clientMap= new ConcurrentHashMap <>();

            if(args.length == 1){
                Server server = Server.getServer();                              //单例模式获取Server
                server.startServer(serverActionContainer,motionContainer,clientMap);
            }
            else if(args.length == 2){
                try {
                    Server server = Server.getServer(Integer.valueOf(args[1]));
                    server.startServer(serverActionContainer,motionContainer,clientMap);                         //单例模式获取Server
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
        //测试用入口  所有参数都为默认参数
        else if("3".equals(args[0])){
            ActionContainer motionContainer = new ActionContainer(); //用于存储主设备动作的信息

            ActionContainer serverActionContainer = new ActionContainer();
            ActionContainer clientActionContainer = new ActionContainer();
            ConcurrentHashMap<String, SystemInfo> clientMap= new ConcurrentHashMap<>();

            Server server = Server.getServer();
            server.startServer(serverActionContainer,motionContainer,clientMap);
            Client client =Client.getClient();
            client.startClient(clientActionContainer);
        }
        else{
            System.out.println("invalid input parameter.");
        }
    }
}
