package common;

public class Shell {
    public static void callShell(String shellString) {
        try {
            Process process = Runtime.getRuntime().exec(shellString);
            int exitValue = process.waitFor();
            if (0 != exitValue) {
                System.out.println("call shell failed. error code is :" + exitValue);
            }
        } catch (Throwable e) {
            System.out.println("call shell failed. " + e);
        }
    }
}
