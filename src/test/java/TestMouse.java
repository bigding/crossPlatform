import motionSimulation.MouseMotion;
import listener.GlobalDeviceListener;

public class TestMouse {
    public static void main(String[] args) {
        TestMouse test = new TestMouse();
        try {
            test.mouseMotionTest();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public  void mouseMotionTest() throws InterruptedException {

    }
}
