import motionSimulation.KeyboardMotion;

public class TestKeyboard {
    public static void main(String[] args) {
        KeyboardMotion.keyDown(0x11);
        KeyboardMotion.keyDown(0x46);
        KeyboardMotion.keyUp(0x46);
        KeyboardMotion.keyUp(0x11);
    }
}
