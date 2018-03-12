package util;

public class MouseWheel {
    int id = 2;  //标识符 2为鼠标滚轮
    int direction;

    public MouseWheel() {
    }

    public MouseWheel(int direction) {
        this.direction = direction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "MouseWheel{" +
                "id=" + id +
                ", direction=" + direction +
                '}';
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

}
