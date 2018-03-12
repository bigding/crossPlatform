package util;

public class Mouse {
    int id = 1;// 标识符 1为鼠标对象
    int posiX, posiY;


    public Mouse() {
    }


    public Mouse(int posiX, int posiY) {
        this.posiX = posiX;
        this.posiY = posiY;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPosiX(int posiX) {
        this.posiX = posiX;
    }

    public void setPosiY(int posiY) {
        this.posiY = posiY;
    }

    public int getPosiX() {
        return posiX;
    }

    public int getPosiY() {
        return posiY;
    }

    @Override
    public String toString() {
        return "Mouse{" +
                "id=" + id +
                ", posiX=" + posiX +
                ", posiY=" + posiY +
                '}';
    }

}
