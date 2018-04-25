package motionSimulation;

import com.sun.jna.Library;
import com.sun.jna.Native;
import common.Shell;
import common.SystemInfo;

/**
 * 通过jni控制鼠标
 */
public class MouseMotion {

    SystemInfo systemInfo;
    /**
     * osType:0为windows 1为linux 2未定义
     */
    static int osType;

    public MouseMotion() {
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

    public interface Mouse extends Library {
        Mouse INSTANCE = (Mouse) Native.loadLibrary("lib/x64/Mouse", Mouse.class);

        public void mouseUp();

        public void mouseDown();

        public void mouseLeft();

        public void mouseRight();

        public void mouseMoveTo(int a, int b);

        public void mouseMoveBy(int a, int b);

        public void mouseLeftDown();

        public void mouseLeftUp();

        public void mouseRightDown();

        public void mouseRightUp();

        public void mouseMiddleDown();

        public void mouseMiddleUp();

        public void mouseWheelRotate(int a);
    }
    

    public static void up() {
        if (osType == 1) {
            Mouse.INSTANCE.mouseUp();
        }
        else if (osType == 0) {
            String shellStr = "xdotool mousemove_relative -- 0 -1";
            Shell.callShell(shellStr);
        }else{

        }
    }

    public static void down() {
        if (osType == 1) {
            Mouse.INSTANCE.mouseDown();
        }
        else if (osType == 0) {
            String shellStr = "xdotool mousemove_relative  0 1";
            Shell.callShell(shellStr);
        }else{

        }
    }

    public static void left() {
        if (osType == 1) {
            Mouse.INSTANCE.mouseLeft();
        }
        else if (osType == 0) {
            String shellStr = "xdotool mousemove_relative -- -1 0";
            Shell.callShell(shellStr);
        }else{

        }
    }

    public static void right() {
        if (osType == 1) {
            Mouse.INSTANCE.mouseRight();
        }
        else if (osType == 0) {
            String shellStr = "xdotool mousemove_relative  1 0";
            Shell.callShell(shellStr);

        }else{

        }
    }

    public static void moveTo(int horizontal, int vertical) {
        if (osType == 1) {
            Mouse.INSTANCE.mouseMoveTo(horizontal, vertical);
        }
        else if (osType == 0) {
            String shellStr = "xdotool mousemove "+horizontal+" "+vertical;
            Shell.callShell(shellStr);
        }else{

        }
    }

    public static void moveBy(int horizontal, int vertical) {
        if (osType == 1) {
            Mouse.INSTANCE.mouseMoveBy(horizontal, vertical);
        }
        else if (osType == 0) {
            String shellStr;
            if(horizontal < 0 || vertical < 0) {
                shellStr = "xdotool mousemove_relative -- "+horizontal+" "+vertical;
        }
            else{
                shellStr = "xdotool mousemove_relative "+horizontal+" "+vertical;
            }
            Shell.callShell(shellStr);
        }else{

        }
    }

    public static void leftDown() {
        if (osType == 1) {
            Mouse.INSTANCE.mouseLeftDown();
        }
        else if (osType == 0) {
            String shellStr = "xdotool mousedown 1";
            Shell.callShell(shellStr);
        }else{

        }
    }

    public static void leftUp() {
        if (osType == 1) {
            Mouse.INSTANCE.mouseLeftUp();
        }
        else if (osType == 0) {
            String shellStr = "xdotool mouseup 1";
            Shell.callShell(shellStr);
        }else{

        }
    }

    public static void rightDown() {
        if (osType == 1) {
            Mouse.INSTANCE.mouseRightDown();
        }
        else if (osType == 0) {
            String shellStr = "xdotool mousedown 3";
            Shell.callShell(shellStr);
        }else{

        }
    }

    public static void rightUp() {
        if (osType == 1) {
            Mouse.INSTANCE.mouseRightUp();
        }
        else if (osType == 0) {
            String shellStr = "xdotool mouseup 3";
            Shell.callShell(shellStr);
        }else{

        }
    }

    public static void middleDown() {
        if(osType == 1) {
            Mouse.INSTANCE.mouseMiddleDown();
        }
        else if (osType == 0) {

            String shellStr = "xdotool mousedown 2";
            Shell.callShell(shellStr);
        }else{

        }
    }

    public static void middleUp() {
        if(osType == 1) {
            Mouse.INSTANCE.mouseMiddleUp();
        }
        else if (osType == 0) {
            String shellStr = "xdotool mouseup 2";
            Shell.callShell(shellStr);

        }else{

        }
    }

    public static void wheelRotate(int distance) {
        if(osType == 1) {
            Mouse.INSTANCE.mouseWheelRotate(distance);
        }
        else if (osType == 0) {
            if(distance > 0){
                String shellStr = "xdotool mouseup 4";
                Shell.callShell(shellStr);
            }
            else {
                String shellStr = "xdotool mouseup 5";
                Shell.callShell(shellStr);
            }
        }else{

        }
    }
}
