import com.alibaba.fastjson.JSON;
import util.Mouse;

public class Test {
    public static void main(String[] args) {
        Mouse mouse = new Mouse(1,2);
        String jsonSting = JSON.toJSONString(mouse);
        System.out.println(jsonSting);
        Mouse mo = JSON.parseObject(jsonSting,Mouse.class);
        System.out.println(mo);
        System.out.println(mo.getPosiX()+"\t"+mo.getPosiY());
    }
}
