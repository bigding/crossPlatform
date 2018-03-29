package net;

import com.alibaba.fastjson.JSONObject;
import common.ActionContainer;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private static Logger log4j = Logger.getLogger(Client.class);

    //如果构造函数没有定义port  那么默认使用下面的值
    public static int port = 10000;//监听的端口号

    Thread serverReadThread,serverWriteThread;
    ServerSocket serverSocket = null;
    ActionContainer serverActionContainer;
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

    public void startServer(ActionContainer serverActionContainer){
        this.serverActionContainer = serverActionContainer;
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
            // 如果有多个客户端连接 可能会出错
            while (true) {
                Socket client = serverSocket.accept();
                //一个客户端连接就开两个线程分别处理读和写
                log4j.info("server listen to port "+port);
                serverReadThread = new Thread(new ServerReadHandlerThread(client));
                serverWriteThread = new Thread(new ServerWriteHandlerThread(client,serverActionContainer));
                serverReadThread.start();
                serverWriteThread.start();
            }
        } catch (Exception e) {
//            e.printStackTrace();
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

/*
 *处理读操作的线程
 */
class ServerReadHandlerThread implements Runnable{
    private Socket client=null;

    public ServerReadHandlerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        try{
            while(true){
                //读取客户端数据
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String say = in.readLine();
                System.out.println("H:"+say);
                if("bye".equals(say.toLowerCase())){
                    Server.getServer().stopServer();
                    break;
                }
                System.out.println("客户端说:" + say);
            }
        }catch(Exception e){
            e.printStackTrace();
//            System.out.println("连接出现了问题");
        }finally{
            if(client != null){
                client = null;
                System.out.println("客户端已断开连接");
            }
        }
    }
}

/*
 * 处理写操作的线程
 */
class ServerWriteHandlerThread implements Runnable{
    private static Logger log4j = Logger.getLogger(ServerWriteHandlerThread.class);


    private Socket client;
    ActionContainer serverActionContainer;

    public ServerWriteHandlerThread(Socket client, ActionContainer serverActionContainer) {
        this.client = client;
        this.serverActionContainer = serverActionContainer;
    }

    @Override
    public void run() {
        PrintWriter out=null;
        try {
            out = new PrintWriter(client.getOutputStream());
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));//从控制台获取输入的内容
        try{
            while(true){
                //向客户端发送信息  actionContainer 内容为键盘鼠标的动作信息
                if (!serverActionContainer.isEmpty()) {
                    JSONObject action = null;
                    try {
                        action = serverActionContainer.poll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    log4j.info("Action:" + action);
                    out.println(action);
                    out.flush();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(client != null){
                client = null;
            }
        }
    }
}