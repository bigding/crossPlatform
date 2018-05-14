import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.org.apache.xml.internal.utils.res.IntArrayWrapper;

/**
 * @description: TODO
 * @author Somnus date 2015年4月1日 下午1:26:56
 */
public class Test {
    public static void main(String[] args) {
       int a = 95;
       String b = Integer.toHexString(a);
        System.out.println(b);
    }
}