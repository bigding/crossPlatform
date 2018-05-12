import common.SystemInfo;

public class Test {
    public static void main(String[] args) throws InterruptedException {
//        SystemInfo systemInfo = SystemInfo.getInstance();
//        systemInfo.PrintInfo();
        int a = 1,b= 2,c;
        c = (a = b);
        System.out.println(a);
    }
}
