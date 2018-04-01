package net;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import common.ActionContainer;
import common.SystemInfo;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

public class Client {
    private static Logger log4j = Logger.getLogger(Client.class);

    // 如果构造函数没有定义ip和port 那么默认使用下面的值
    public static  String ip = "localhost";//服务器地址
    public static  int port = 10000;//服务器端口号

    Thread clientReadThread,clientWriteThread;
    Socket clientSocket;
    ActionContainer clientActionContainer;
    static volatile Client client = null;

    private Client(){

    }
    private Client(String ip, int port){
        this.ip = ip;
        this.port = port;
    }
    public static Client getClient(){
        if(client == null){
            synchronized (Client.class){
                if(client == null){
                    client = new Client();
                }
            }
        }
        return client;
    }

    public static Client getClient(String ip, int port){
        if(client == null){
            synchronized (Client.class){
                if(client == null){
                    client = new Client(ip,port);
                }
            }
        }
        return client;
    }

    public void startClient(ActionContainer clientActionContainer){
        //初始化客户端存储容器
        this.clientActionContainer = clientActionContainer;
        //将系统信息序列化为json字符串,存入容器中
        SystemInfo systemInfo = SystemInfo.getInstance();
        JSONObject JsonSysInfo = new JSONObject();
        JsonSysInfo.put("id","sys_info");
        String sysInfoStr = JSON.toJSONString(systemInfo);
        JsonSysInfo.put("context",sysInfoStr);
        try {
            this.clientActionContainer.offer(JsonSysInfo);
        } catch (InterruptedException e) {
//            e.printStackTrace();
            log4j.error("failed when add system information to clientActionContainer.");
        }
        handler();
    }
    public void stopClient(){
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clientReadThread.interrupt();
        clientWriteThread.interrupt();
    }
    private  void handler(){
        try {
            //实例化一个Socket，并指定服务器地址和端口
            clientSocket = new Socket(ip, port);
            log4j.info("client connected to "+ip+"\t"+port);
            //开启两个线程，一个负责读，一个负责写
            clientReadThread = new  Thread(new ClientReadHandlerThread(clientSocket));
            clientWriteThread = new Thread(new ClientWriteHandlerThread(clientSocket,clientActionContainer));
            clientWriteThread.start();
            clientReadThread.start();
        } catch (Exception e) {
//            e.printStackTrace();
            log4j.error("connect to server fail.");
        }
    }
    private void closeClient(){
        clientReadThread.interrupt();
        clientWriteThread.interrupt();
        try {
            clientSocket.close();
        } catch (IOException e) {
            log4j.error("close client socket fail");
//            e.printStackTrace();
        }
    }
}

