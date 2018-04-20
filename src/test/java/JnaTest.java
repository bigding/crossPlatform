import com.sun.jna.Library;
import com.sun.jna.Native;

public class JnaTest {
    public interface Jna extends Library{
        Jna INSTANCE = (Jna) Native.loadLibrary("lib/x64/mouseShow",Jna.class);

        public void show();
        public void hide();
    }

    public static void main(String[] args) throws InterruptedException {
        Jna.INSTANCE.show();
        Thread.sleep(10000);
        Jna.INSTANCE.hide();
    }
}
