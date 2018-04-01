package net;

import com.alibaba.fastjson.JSONObject;
import common.ActionContainer;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

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
        log4j.info("server write thread started.");
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
