import motionSimulation.KeyboardMotion;

import java.awt.event.KeyEvent;

public class KeyBoardTest {
    public static void main(String[] args) {
        KeyboardMotion.keyDown(KeyEvent.VK_CONTROL);
        KeyboardMotion.keyDown(KeyEvent.VK_F);
        KeyboardMotion.keyUp(KeyEvent.VK_F);
        KeyboardMotion.keyUp(KeyEvent.VK_CONTROL);
    }
}
