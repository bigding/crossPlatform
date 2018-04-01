package net;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import common.SystemInfo;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/*
 *处理读操作的线程
 */
class ServerReadHandlerThread implements Runnable{
    private static Logger log4j = Logger.getLogger(ServerReadHandlerThread.class);

    private Socket client=null;
    ConcurrentHashMap <String, SystemInfo> clientMap;

    public ServerReadHandlerThread(Socket client, ConcurrentHashMap<String, SystemInfo> clientMap) {
        this.client = client;
        this.clientMap = clientMap;
        log4j.info("server read thread started.");
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
                    SystemInfo clientSysInfo = JSON.parseObject(say,SystemInfo.class);
                    clientMap.put("client",clientSysInfo);
                    log4j.info("get client system info.");
                    System.out.println("size:"+clientMap.size());
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
