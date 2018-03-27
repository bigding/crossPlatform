import controller.DisableMotion;

public class TestDisableMotion {
    public static void main(String[] args) {
        DisableMotion.disableAll();
        for(int i = 0; i < 100; i++){
            try {
                System.out.println(i);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        DisableMotion.enableAll();
    }
}
