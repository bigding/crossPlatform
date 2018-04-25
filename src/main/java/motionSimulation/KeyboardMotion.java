package motionSimulation;

import com.sun.jna.Library;
import com.sun.jna.Native;
import common.Shell;
import common.SystemInfo;

/**
 * 通过jni控制键盘事件
 */
public class KeyboardMotion {

    SystemInfo systemInfo;
    /**
     * osType:0为windows 1为linux 2未定义
     */
    static int osType;

    public KeyboardMotion() {
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
    public static void keyDown(int a){
        if(osType == 1) {
            KeyBoard.INSTANCE.keyDown(a);
        }
        else if(osType == 0){
            String shellStr = "xdotool keydown "+a;
            Shell.callShell(shellStr);
        }
        else {

        }
    }
    public static void keyUp(int a){
        if(osType == 1) {
            KeyBoard.INSTANCE.keyUp(a);
        }
        else if(osType == 0){
            String shellStr = "xdotool keyup "+a;
            Shell.callShell(shellStr);
        }
        else{

        }
    }
}
