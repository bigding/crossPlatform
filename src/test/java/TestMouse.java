import motionSimulation.MouseMotion;
import listener.ActionListener;
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
        GlobalDeviceListener globalDeviceListener = new GlobalDeviceListener();
        globalDeviceListener.startGlobalListener();
        ActionListener actionListener = new ActionListener();
        Thread listen = new Thread(actionListener);
        listen.start();
        for (int i = 0; i < 100; i++) {
            MouseMotion.left();
            Thread.sleep(10);
        }
        MouseMotion.up();
        MouseMotion.right();
        MouseMotion.down();
        MouseMotion.moveTo(0,0);
        MouseMotion.moveBy(500,500);
        MouseMotion.leftDown();
        MouseMotion.leftUp();
        MouseMotion.rightDown();
        MouseMotion.rightUp();
        MouseMotion.middleDown();
        MouseMotion.middleUp();
        MouseMotion.wheelRotate(10);
        listen.interrupt();
        globalDeviceListener.removeGlobalListener();
    }
}
