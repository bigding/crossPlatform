import hook.KeyHook;
import hook.MouseHook;

import java.util.Properties;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        MouseHook mouseHook = new MouseHook();
        Thread mThread = new Thread(){
            @Override
            public void run() {
                mouseHook.startMouseHook();
            }
        };
        mThread.start();
        Thread.sleep(10000);
        mouseHook.stopMouseHook();
        mThread.interrupt();


//        KeyHook keyHook = new KeyHook();
//        Thread thread = new Thread(){
//            @Override
//            public void run() {
//                keyHook.startKeyHook();
//            }
//        };
//        thread.start();
//        Thread.sleep(10000);
//        keyHook.stopKeyHook();
//        thread.interrupt();
//        Properties props=System.getProperties();
//
//        System.out.println(props.getProperty("os.name"));
//        System.out.println(props.getProperty("os.version"));
//        System.out.println(props.getProperty("os.arch"));
    }
}
