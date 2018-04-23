import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ShellTest {
    public static void main(String args[]) {
//        Process process = null;
//        List<String> processList = new ArrayList<String>();
//        try {
//            process = Runtime.getRuntime().exec("ps -aux");
//            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line = "";
//            while ((line = input.readLine()) != null) {
//                processList.add(line);
//            }
//            input.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        for (String line : processList) {
//            System.out.println(line);
//        }
        callShell("xdotool mousemove 1 1");
    }



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