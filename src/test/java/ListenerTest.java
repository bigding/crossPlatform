import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;
import common.ActionContainer;
import common.SystemInfo;
import hook.KeyHook;
import hook.MouseHook;
import listener.GlobalDeviceListener;
import net.Client;
import net.Server;

import java.util.concurrent.ConcurrentHashMap;

public class ListenerTest
{
    public static void main(String[] args) throws InterruptedException {
        ActionContainer motionContainer = new ActionContainer(); //用于存储主设备动作的信息

        new Thread(){
            @Override
            public void run() {
                while (true){
                    if(!motionContainer.isEmpty()){
                        try {
                            System.out.println(motionContainer.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();

        GlobalDeviceListener listener = new GlobalDeviceListener();
        listener.startGlobalListener(motionContainer);
        listener.changeHookStatus(true);


        MouseHook mouseHook = new MouseHook();
        KeyHook keyHook = new KeyHook(motionContainer);
        Thread keyThread = new Thread(){
            @Override
            public void run() {
                keyHook.startKeyHook();
            }
        };
        Thread mouseThread = new Thread(){
            @Override
            public void run() {
                mouseHook.startMouseHook();
            }
        };
        keyThread.start();
        mouseThread.start();
        Thread.sleep(10000);
        mouseHook.stopMouseHook();
        keyHook.stopKeyHook();
        listener.changeHookStatus(false);
        listener.removeGlobalListener();
        keyThread.interrupt();
        mouseThread.interrupt();
    }
}