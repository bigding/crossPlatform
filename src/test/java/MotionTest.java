import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.WString;
import motionSimulation.MouseMotion;

public class MotionTest {

    public static void main(String[] args) {
        MouseMotion.moveTo(0, 0);
    }
}
