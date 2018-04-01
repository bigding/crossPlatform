package net;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import common.SystemInfo;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/*
 *处理读操作的线程
 */
class ServerReadHandlerThread implements Runnable{
    private static Logger log4j = Logger.getLogger(ServerReadHandlerThread.class);

    private Socket client=null;
    private SystemInfo clientSysInfo = null;

    public ServerReadHandlerThread(Socket client,SystemInfo clientSysInfo) {
        this.client = client;
        this.clientSysInfo = clientSysInfo;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        try{
            while(true){
                //读取客户端数据
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String say = in.readLine();
                JSONObject jsonClientSay = JSONObject.parseObject(say);
                //获取客户端的设备信息
                if("sys_info".equals(jsonClientSay.get("id"))){
                    this.clientSysInfo = JSON.parseObject(say,SystemInfo.class);
                    log4j.info("get client system info.");
                }
                else{
                    log4j.warn("no use information from client: "+say);
                }
            }
        }catch(Exception e){
            log4j.error("connection error.");
        }finally{
            if(client != null){
                client = null;
                log4j.info("connection disconnect");
            }
        }
    }
}
