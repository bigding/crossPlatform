package motionSimulation;

import com.sun.jna.Library;
import com.sun.jna.Native;
import common.Shell;
import common.SystemInfo;
import util.Key;

/**
 * 通过jni控制键盘事件
 */
public class KeyboardMotion {

    private static  KeyboardMotion keyboardMotion = null;

    SystemInfo systemInfo;
    /**
     * osType:0为windows 1为linux 2未定义
     */
    private int osType;

    public static  KeyboardMotion getInstance() {
        if(keyboardMotion == null){
            synchronized(KeyboardMotion.class){
                if(keyboardMotion == null){
                    keyboardMotion =  new KeyboardMotion();
                }
            }
            return keyboardMotion;
        }
        return keyboardMotion;
    }

    private KeyboardMotion() {
        systemInfo = SystemInfo.getInstance();
        String osName = systemInfo.getOsName();
        if (osName.toLowerCase().contains("windows")) {
            osType = 0;
        } else if (osName.toLowerCase().contains("linux")) {
            osType = 1;
        } else {
            osType = 2;
        }
    }

    public interface KeyBoard extends Library {
        KeyBoard INSTANCE = ( KeyBoard) Native.loadLibrary("lib/x64/KeyBoard",  KeyBoard.class);
        public void keyDown(int a);
        public void keyUp(int a);
    }
    public synchronized void keyDown(int a){
        if(osType == 0) {
            KeyBoard.INSTANCE.keyDown(a);
        }
        else if(osType == 1){
            String hexStr = Key.linuxGetKeyCode(a);
            String shellStr = "xdotool keydown 0x"+hexStr;
            Shell.callShell(shellStr);
        }
        else {

        }
    }
    public synchronized void keyUp(int a){
        if(osType == 0) {
            KeyBoard.INSTANCE.keyUp(a);
        }
        else if(osType == 1){
            String hexStr = Key.linuxGetKeyCode(a);
            String shellStr = "xdotool keyup 0x"+hexStr;
            Shell.callShell(shellStr);
        }
        else{

        }
    }
}
