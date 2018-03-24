import controller.MouseMotion;

public class Test {
    public static void main(String[] args) {
        try {
            MouseMotion();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void MouseMotion() throws InterruptedException {
        for(int i = 0; i < 100; i++){
            MouseMotion.left();
            Thread.sleep(10);
        }
        MouseMotion.leftDown();
        MouseMotion.leftUp();
        for (int i = 0; i < 100; i++) {
            MouseMotion.up();
            Thread.sleep(10);
        }
        for (int i = 0; i < 100; i++) {
            MouseMotion.right();
            Thread.sleep(10);
        }
        for (int i = 0; i < 100; i++) {
            MouseMotion.down();
            Thread.sleep(10);
        }
        MouseMotion.moveTo(0,0);
        MouseMotion.moveBy(500,500);

    }
}
