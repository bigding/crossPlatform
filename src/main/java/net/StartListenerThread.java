package net;

import common.ActionContainer;
import common.SystemInfo;
import listener.GlobalDeviceListener;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

class StartListenerThread implements Runnable{
    private static Logger log4j = Logger.getLogger(StartListenerThread.class);

    ActionContainer serverActionContainer;
    ConcurrentHashMap<String, SystemInfo> clientMap;

    StartListenerThread(ActionContainer serverActionContainer, ConcurrentHashMap<String, SystemInfo> clientMap){
        this.serverActionContainer = serverActionContainer;
        this.clientMap = clientMap;
    }

    @Override
    public void run() {
        while (true){
//            System.out.println("is:"+clientMap.size());
//            log4j.info("map size:"+clientMap.size());
            if(clientMap.size() != 0){
                GlobalDeviceListener globalDeviceListener = new GlobalDeviceListener();
                globalDeviceListener.startGlobalListener(serverActionContainer);
                log4j.info("server start listen to global device event.");
                break;
            }
        }
    }
}