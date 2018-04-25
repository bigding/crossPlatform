package net;

import common.ActionContainer;
import common.SystemInfo;
import control.DeviceMotionConvert;
import listener.GlobalMouseListener;
import org.apache.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;

class StartListenerThread implements Runnable{
    private static Logger log4j = Logger.getLogger(StartListenerThread.class);

    ActionContainer mouseMoveContainer,serverActionContainer;
    ConcurrentHashMap<String, SystemInfo> clientMap;

    StartListenerThread(ActionContainer mouseMoveContainer,
                        ActionContainer serverActionContainer,
                        ConcurrentHashMap<String, SystemInfo> clientMap){
        this.mouseMoveContainer = mouseMoveContainer;
        this.serverActionContainer = serverActionContainer;
        this.clientMap = clientMap;
    }

    @Override
    public void run() {
        while (true){
            if(clientMap.size() != 0){
                //开启设备监听
                GlobalMouseListener globalMouseListener = new GlobalMouseListener();
                globalMouseListener.startGlobalListener(mouseMoveContainer);
                log4j.info("server start listen to global device event.");
                // 对一些必要的信息进行转换, 主要是鼠标位置向鼠标移动动作的转换,并控制何时传输鼠标点击和键盘信息
                Thread motionConvertThread = new Thread(new DeviceMotionConvert(mouseMoveContainer,serverActionContainer,clientMap));
                motionConvertThread.start();
                log4j.info("motion convert started.");

                break;
            }
        }
    }
}