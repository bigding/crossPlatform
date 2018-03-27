package net;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server  {
    private static Logger logger = Logger.getLogger(Client.class);

    public static final int PORT = 10000;//监听的端口号

    Thread serverReadThread,serverWriteThread;
    ServerSocket serverSocket = null;

    public static void main(String[] args) {
        System.out.println("sever begins");
        Server server = new Server();
        server.init();
    }

    public void init() {
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                Socket client = serverSocket.accept();
                //一个客户端连接就开两个线程分别处理读和写
                serverReadThread = new Thread(new ServerReadHandlerThread(client));
                serverWriteThread = new Thread(new ServerWriteHandlerThread(client));
                serverReadThread.start();
                serverWriteThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
                if("bye".equals(say.toLowerCase())){
                    break;
                }
                System.out.println("客户端说:" + say);
            }
        }catch(Exception e){
//            e.printStackTrace();
            System.out.println("连接出现了问题==");
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
    private Socket client;

    public ServerWriteHandlerThread(Socket client) {
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
        try{
            while(true){
                //向客户端回复信息
                out.println(reader.readLine());
                out.flush();
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