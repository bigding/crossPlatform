import common.SystemInfo;
import hook.KeyHook;
import hook.MouseHook;

import java.util.Properties;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        SystemInfo systemInfo = SystemInfo.getInstance();
        systemInfo.PrintInfo();
    }
}
