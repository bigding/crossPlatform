package controller;

import java.awt.*;

public class MouseController {
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

    public static void main(String[] args) {
        while (true){
            MouseController mouse = new MouseController();
            System.out.println(mouse.getMousePosi());
        }
    }
}
