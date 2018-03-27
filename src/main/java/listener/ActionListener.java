package listener;

import com.alibaba.fastjson.JSONObject;
import common.ActionContainer;
import org.apache.log4j.Logger;

/**
 * 将LinkedBlockingDeque 中存的信息取出来,以备后续使用
 */
public class ActionListener implements Runnable{
    private static Logger logger = Logger.getLogger(ActionListener.class);
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
                logger.info("Action:" + action);
            }
        }
    }
}
