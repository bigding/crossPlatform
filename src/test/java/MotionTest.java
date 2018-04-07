import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.WString;

public class MotionTest {
    public interface TestDll extends Library {
        TestDll INSTANCE = (TestDll) Native.loadLibrary("lib/Mouse", TestDll.class);
        public void mouseMoveTo(int a, int b);
    }

    public static void main(String[] args) {
        TestDll.INSTANCE.mouseMoveTo(0, 0);
    }
}
