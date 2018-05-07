package net;

import com.alibaba.fastjson.JSONObject;
import motionSimulation.KeyboardMotion;
import motionSimulation.MouseMotion;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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
        log4j.info("client read thread started.");
    }
    @Override
    public void run() {
        BufferedReader in = null;
        try {
            while(true){
                //读取服务器端传来的数据
                InputStream inputStream = client.getInputStream();
//                if(inputStream.available() == 0){
//
//                }
                in = new BufferedReader(new InputStreamReader(inputStream));
                String inStr = in.readLine();
                //可能因为网络状况等原因 传输的数据会出现错误,要对接收到的数据严格验证,不合要求的数据直接抛弃
                if(inStr == ""|| inStr.isEmpty()) {
                    continue;
                }
                JSONObject action;
                try{
                    action = JSONObject.parseObject(inStr);
                }catch (Exception e){
                    continue;
                }
                log4j.info("server: " + action);
                if("2".equals(action.getString("id"))){
                    if("1".equals(action.getString("press"))){
                        MouseMotion.leftDown();
                    }
                    else{
                        MouseMotion.rightDown();
                    }
                }
                else if("3".equals(action.getString("id"))){
                    if("1".equals(action.getString("release"))){
                        MouseMotion.leftUp();
                    }
                    else{
                        MouseMotion.rightUp();
                    }
                }
                else if("6".equals(action.getString("id"))){
                    MouseMotion.wheelRotate((int)action.get("wheelRotation"));
                }
                else if("7".equals(action.getString("id"))){
                    KeyboardMotion.keyDown((int)action.get("keyCode"));
                }
                else if("8".equals(action.getString("id"))){
                    KeyboardMotion.keyUp((int)action.get("keyCode"));
                }
                else if("to".equals(action.getString("id"))){
                    MouseMotion.moveTo((int)action.get("posiX"),(int)action.get("posiY"));
                }
                else{
                    log4j.warn("meaningless motion:"+action);
                }
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
