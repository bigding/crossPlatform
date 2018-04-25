import common.ActionContainer;
import hook.KeyHook;
import hook.MouseHook;
import listener.GlobalMouseListener;

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

        GlobalMouseListener listener = new GlobalMouseListener();
        listener.startGlobalListener(motionContainer);


        MouseHook mouseHook = new MouseHook(motionContainer);
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
        listener.removeGlobalListener();
        keyThread.interrupt();
        mouseThread.interrupt();
    }
}