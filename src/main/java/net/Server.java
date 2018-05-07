package net;

import common.ActionContainer;
import common.SystemInfo;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class Server extends Thread {
    private static Logger log4j = Logger.getLogger(Client.class);

    //如果构造函数没有定义port  那么默认使用下面的值
    public static int port = 10000;//监听的端口号

    Thread serverReadThread,serverWriteThread,waitStartListenerThread;
    ServerSocket serverSocket = null;
    ActionContainer serverActionContainer,mouseMoveContainer;
    ConcurrentHashMap <String, SystemInfo> clientMap;
    static volatile Server server = null;

    private Server(){

    }
    private Server(int port){
        this.port = port;
    }

    public static Server getServer(){
        if(server == null){
            synchronized (Client.class){
                if(server == null){
                    server = new Server();
                }
            }
        }
        return server;
    }
    public static Server getServer(int port){
        if(server == null){
            synchronized (Client.class){
                if(server == null){
                    server = new Server(port);
                }
            }
        }
        return server;
    }

    public void  startServer(ActionContainer serverActionContainer,
                            ActionContainer mouseMoveContainer,
                            ConcurrentHashMap<String, SystemInfo> clientMap){
        this.serverActionContainer = serverActionContainer;
        this.mouseMoveContainer = mouseMoveContainer;
        this.clientMap = clientMap;
        start();  //启动线程监听客户端响应
    }
    public void stopServer(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        serverWriteThread.interrupt();
        serverReadThread.interrupt();
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            log4j.info("server listen to port "+port);
            // 如果有多个客户端连接 可能会出错
            while (true) {
                Socket client = serverSocket.accept();
                //新建一个线程等待,当客户端系统信息传输到服务端后,开启服务器端的全局监听器,
                // 并将监听器监听到的消息存放在专用的容器中,并且开启处理鼠标键盘信息的线程
                waitStartListenerThread = new Thread(new StartListenerThread(mouseMoveContainer,serverActionContainer,clientMap));
                //一个客户端连接就开两个线程分别处理读和写
                serverReadThread = new Thread(new ServerReadHandlerThread(client,clientMap));
                serverWriteThread = new Thread(new ServerWriteHandlerThread(client,serverActionContainer));

                serverReadThread.start();
                serverWriteThread.start();
                waitStartListenerThread.start();
            }
        } catch (Exception e) {
            log4j.error("listen to port fail.");
        } finally{
            try {
                if(serverSocket != null){
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

