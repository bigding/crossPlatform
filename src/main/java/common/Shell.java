package common;

import org.apache.log4j.Logger;

public class Shell {
    private static Logger log4j = Logger.getLogger(Shell.class);

    public static void callShell(String shellString) {
        try {
            Process process = Runtime.getRuntime().exec(shellString);
            int exitValue = process.waitFor();
            if (0 != exitValue) {
                log4j.error("call shell failed. error code is :" + exitValue);
            }
        } catch (Throwable e) {
            log4j.info("call shell failed. " + e);
        }
    }
}
