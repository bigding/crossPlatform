package net;

import common.ActionContainer;
import org.apache.log4j.Logger;

import javax.swing.event.CaretListener;
import java.io.*;
import java.net.Socket;

public class Client {
    private static Logger logger = Logger.getLogger(Client.class);

    public static  String ip = "localhost";//服务器地址
    public static  int port = 10000;//服务器端口号

    Thread clientReadThread,clientWriteThread;
    Socket clientSocket;
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
        //初始化动作容器
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
            logger.info("connected to"+ip+"\t"+port);
            //开启两个线程，一个负责读，一个负责写
            clientReadThread = new  Thread(new ClientReadHandlerThread(clientSocket));
            clientWriteThread = new Thread(new ClientWriteHandlerThread(clientSocket));
            clientWriteThread.start();
            clientReadThread.start();
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("connect to server fail.");
        }
    }
    private void closeClient(){
        clientReadThread.interrupt();
        clientWriteThread.interrupt();
        try {
            clientSocket.close();
        } catch (IOException e) {
            logger.error("close client socket fail");
//            e.printStackTrace();
        }
    }
}

/*
 *处理读操作的线程
 */
class ClientReadHandlerThread implements Runnable{
    private Socket client;

    public ClientReadHandlerThread(Socket client) {
        this.client = client;
    }
    @Override
    public void run() {
        BufferedReader in = null;
        try {
            while(true){
                //读取客户端数据
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                System.out.println("服务器端说:" + in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(client != null){
                client = null;
            }
        }
    }
}

/*
 * 处理写操作的线程
 */
class ClientWriteHandlerThread implements Runnable{
    private Socket client;

    public ClientWriteHandlerThread(Socket client) {
        this.client = client;
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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));//从控制台获取输入的内容
        try {
            while(true){
                String read = reader.readLine();
                out.println(read);
                out.flush();
                if("bye".equals(read.toLowerCase())){
                    Client.getClient().stopClient();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            client.close();
            reader.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        out.close();
    }
}