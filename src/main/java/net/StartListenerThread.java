package net;

import common.ActionContainer;
import common.SystemInfo;
import listener.GlobalDeviceListener;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

class StartListenerThread implements Runnable{
    private static Logger log4j = Logger.getLogger(StartListenerThread.class);

    ActionContainer motionContainer;
    ConcurrentHashMap<String, SystemInfo> clientMap;

    StartListenerThread(ActionContainer motionContainer, ConcurrentHashMap<String, SystemInfo> clientMap){
        this.motionContainer = motionContainer;
        this.clientMap = clientMap;
    }

    @Override
    public void run() {
        while (true){
            if(clientMap.size() != 0){
                GlobalDeviceListener globalDeviceListener = new GlobalDeviceListener();
                globalDeviceListener.startGlobalListener(motionContainer);
                log4j.info("server start listen to global device event.");
                break;
            }
        }
    }
}