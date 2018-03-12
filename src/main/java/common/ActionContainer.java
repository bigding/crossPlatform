package common;

import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.LinkedBlockingDeque;

public class ActionContainer {

    static LinkedBlockingDeque<JSONObject> queue = new LinkedBlockingDeque<>();


    public synchronized void offer(JSONObject obj) throws InterruptedException {
        while (queue.size() == Integer.MAX_VALUE) {
            wait();
        }
        queue.offer(obj);
        notifyAll();
    }

    public synchronized JSONObject poll() throws InterruptedException {
        while (queue.size() <= 0) {
            wait();
        }
        JSONObject obj = queue.poll();
        notifyAll();
        return obj;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
