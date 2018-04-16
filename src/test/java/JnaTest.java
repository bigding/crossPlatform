import com.sun.jna.Library;
import com.sun.jna.Native;

public class JnaTest {
    public interface Jna extends Library{
        Jna INSTANCE = (Jna) Native.loadLibrary("lib/JnaTest",Jna.class);

        public int add(int a,int b);
    }

    public static void main(String[] args) {
        System.out.println(Jna.INSTANCE.add(2,3));
    }
}
