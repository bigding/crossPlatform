package hook;

import common.ActionContainer;

public class HookControl {
    Thread keyThread,mouseThread;
    MouseHook mouseHook;
    KeyHook keyHook;
    ActionContainer serverActionContainer;

    public HookControl(ActionContainer container){
        serverActionContainer = container;
    }
    public void startHook(){
        mouseHook = new MouseHook(serverActionContainer);
        keyHook = new KeyHook(serverActionContainer);
        keyThread = new Thread(){
            @Override
            public void run() {
                keyHook.startKeyHook();
            }
        };
        mouseThread = new Thread(){
            @Override
            public void run() {
                mouseHook.startMouseHook();
            }
        };
        keyThread.start();
        mouseThread.start();
    }
    public void stopHook() throws InterruptedException {
        mouseHook.stopMouseHook();
        keyHook.stopKeyHook();
        keyThread.interrupt();
        mouseThread.interrupt();
        keyThread.join();
        mouseThread.join();
    }
    public boolean isStop(){
        return keyThread.isAlive() || mouseThread.isAlive();
    }

    public void enableInput(){
        mouseHook.enableInput();
        keyHook.enableInput();
    }

    public void disableInput(){
        mouseHook.disableInput();
        keyHook.disableInput();
    }
}
