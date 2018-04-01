package net;

import common.ActionContainer;
import common.SystemInfo;
import listener.GlobalDeviceListener;

class StartListenerThread implements Runnable{
    ActionContainer serverActionContainer = null;
    SystemInfo clientSysInfo = null;

    StartListenerThread(ActionContainer serverActionContainer, SystemInfo clientSysInfo){
        this.serverActionContainer = serverActionContainer;
        this.clientSysInfo = clientSysInfo;
    }

    @Override
    public void run() {
        while (true){
            if(clientSysInfo != null){
                GlobalDeviceListener globalDeviceListener = new GlobalDeviceListener();
                globalDeviceListener.startGlobalListener(serverActionContainer);
                break;
            }
        }
    }
}