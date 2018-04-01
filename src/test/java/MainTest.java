import com.alibaba.fastjson.JSON;
import common.SystemInfo;
import org.apache.log4j.Logger;
import sun.applet.Main;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainTest {
    private static Logger logger = Logger.getLogger(MainTest.class);
    public static void main(String[] args) {
        SystemInfo systemInfo =  SystemInfo.getInstance();
        String info = JSON.toJSONString(systemInfo);
        System.out.println(info);
    }
}
