package util;

import java.awt.*;

public class Mouse {
    PointerInfo pointerInfo = MouseInfo.getPointerInfo();
    Point point = pointerInfo.getLocation();

    public double getMouseX() {
        return point.getX();
    }

    public double getMouseY() {
        return point.getY();
    }

    public String getMousePosi(){
        return "x:"+getMouseX()+"\ty:"+getMouseY();
    }
}
