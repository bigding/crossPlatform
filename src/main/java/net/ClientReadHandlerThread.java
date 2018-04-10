package net;

import com.alibaba.fastjson.JSONObject;
import motionSimulation.KeyboardMotion;
import motionSimulation.MouseMotion;
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
        log4j.info("client read thread started.");
    }
    @Override
    public void run() {
        BufferedReader in = null;
        try {
            while(true){
                //读取服务器端传来的数据
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                JSONObject action = JSONObject.parseObject(in.readLine());
                log4j.info("server: " + action);
                if("2".equals(action.get("id"))){
                    if("1".equals(action.get("press"))){
                        MouseMotion.leftDown();
                    }
                    else{
                        MouseMotion.rightDown();
                    }
                }
                else if("3".equals(action.get("id"))){
                    if("1".equals(action.get("press"))){
                        MouseMotion.leftUp();
                    }
                    else{
                        MouseMotion.rightUp();
                    }
                }
                else if("6".equals(action.get("id"))){
                    MouseMotion.wheelRotate((int)action.get("wheelRotation"));
                }
                else if("7".equals(action.get("id"))){
                    KeyboardMotion.keyDown((int)action.get("keyCode"));
                }
                else if("8".equals(action.get("id"))){
                    KeyboardMotion.keyUp((int)action.get("keyCode"));
                }
                else if("to".equals(action.get("id"))){
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
