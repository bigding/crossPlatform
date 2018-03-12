package util;

public class Keyboard {
    int id = 3; // 标识符 3为鼠标
    int keyState; // 键是按下(1)还是松开(0)
    String keyNum;

    public Keyboard() {
    }



    public Keyboard(int keyState, String keyNum) {
        this.keyState = keyState;
        this.keyNum = keyNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKeyState() {
        return keyState;
    }

    public void setKeyState(int keyState) {
        this.keyState = keyState;
    }

    public String getKeyNum() {
        return keyNum;
    }

    public void setKeyNum(String keyNum) {
        this.keyNum = keyNum;
    }
    @Override
    public String toString() {
        return "Keyboard{" +
                "id=" + id +
                ", keyState=" + keyState +
                ", keyNum='" + keyNum + '\'' +
                '}';
    }

}
