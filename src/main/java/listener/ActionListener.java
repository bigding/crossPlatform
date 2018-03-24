package listener;

import com.alibaba.fastjson.JSONObject;
import common.ActionContainer;

/**
 * 将LinkedBlockingDeque 中存的信息取出来,以备后续使用
 */
public class ActionListener implements Runnable{
    ActionContainer actionContainer = new ActionContainer();
//    ActionContainer actionContainer = ActionContainer.getActionContainer();
    public ActionListener() throws InterruptedException {

    }
    @Override
    public void run() {
        while(true) {
            if (!actionContainer.isEmpty()) {
                JSONObject action = null;
                try {
                    action = actionContainer.poll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Action:" + action);
            }
        }
    }
}
