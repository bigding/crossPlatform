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
                System.out.println("before key hook");
                keyHook.startKeyHook();
                System.out.println("after key hook");
            }
        };
        mouseThread = new Thread(){
            @Override
            public void run() {
                System.out.println("before mouse hook");
                mouseHook.startMouseHook();
                System.out.println("after mouse hook");
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
