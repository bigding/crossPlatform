package common;

import com.alibaba.fastjson.JSONObject;
import listener.ActionListener;

import java.util.concurrent.LinkedBlockingDeque;

/*使用单例模式书写,线程安全*/
public class ActionContainer {
//    private static volatile ActionContainer actionContainer = null;
//    private ActionContainer(){}
//    public static ActionContainer getActionContainer(){
//        if(actionContainer == null){
//            synchronized(ActionContainer.class){
//                if(actionContainer == null){
//                    actionContainer = new ActionContainer();
//                }
//            }
//        }
//        return actionContainer;
//    }

    // 以下的部分参照 <<java多线程设计模式>> 生产者消费者章节
    static LinkedBlockingDeque<JSONObject> queue = new LinkedBlockingDeque<>();
    static int queueCount = 0;

    public void offer(JSONObject obj) throws InterruptedException {
        if(queueCount >= queue.size()){
            wait();
        }
        System.out.println("******");
        queue.offer(obj);
        queueCount++;
        notifyAll();
    }
    public JSONObject poll() throws InterruptedException {
        if(queueCount <= 0){
            wait();
        }
        JSONObject obj = queue.poll();
        queueCount--;
        notifyAll();
        return  obj;
    }
    public boolean isEmpty(){
        return queue.isEmpty();
    }
}
