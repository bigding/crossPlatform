import common.SystemInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainTest {
    static class OOm{}
    public static void main(String[] args) {
        SystemInfo systemInfo =  SystemInfo.getInstance();
        systemInfo.PrintInfo();
    }
}
