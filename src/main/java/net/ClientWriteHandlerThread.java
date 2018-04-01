package net;

import com.alibaba.fastjson.JSONObject;
import common.ActionContainer;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * 处理写操作的线程
 * 这里的输入是通过标准控制台输入的
 */
class ClientWriteHandlerThread implements Runnable{
    private static Logger log4j = Logger.getLogger(ClientWriteHandlerThread.class);

    private Socket client;
    ActionContainer clientActionContainer;

    public ClientWriteHandlerThread(Socket client,ActionContainer clientActionContainer) {
        this.client = client;
        this.clientActionContainer = clientActionContainer;
    }

    @Override
    public void run() {
        PrintWriter out = null;
        try {
            out = new PrintWriter(client.getOutputStream());
        } catch (IOException e1) {
            // TODO Auto-generated catch block
//            e1.printStackTrace();
            log4j.error("failed when new PrintWriter");
        }
         try {
            while(true){
                //向服务器端发送一些必要的信息,也是通过对一个容器的读取
                if(!clientActionContainer.isEmpty()){
                    JSONObject action = null;
                    try {
                        action = clientActionContainer.poll();
                    } catch (InterruptedException e) {
//                        e.printStackTrace();
                        log4j.error("failed to read action from clientActionContainer");
                    }
                    out.println(action);
                    out.flush();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
         }finally {
            if(client != null){
                client = null;
            }
         }
    }
}
