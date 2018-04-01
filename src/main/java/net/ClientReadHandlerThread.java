package net;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/*
 *处理读操作的线程
 */
class ClientReadHandlerThread implements Runnable{
    private static Logger log4j = Logger.getLogger(ClientReadHandlerThread.class);

    private Socket client;

    public ClientReadHandlerThread(Socket client) {
        this.client = client;
    }
    @Override
    public void run() {
        BufferedReader in = null;
        try {
            while(true){
                //读取服务器端传来的数据
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                log4j.info("server: " + in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
            log4j.error("read data from server fail.");
        } finally{
            if(client != null){
                client = null;
            }
        }
    }
}
