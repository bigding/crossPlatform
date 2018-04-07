package motionSimulation;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * 通过jni控制键盘事件
 */
public class KeyboardMotion {
    public interface KeyBoard extends Library {
        KeyBoard INSTANCE = ( KeyBoard) Native.loadLibrary("lib/KeyBoard",  KeyBoard.class);
        public void keyDown(int a);
        public void keyUp(int a);
    }
    public static void keyDown(int a){
        KeyBoard.INSTANCE.keyDown(a);
    }
    public static void keyUp(int a){
        KeyBoard.INSTANCE.keyUp(a);
    }
}
