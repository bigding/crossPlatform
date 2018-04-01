package control;

import com.alibaba.fastjson.JSONObject;
import common.ActionContainer;
import common.SystemInfo;
import org.apache.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 读取鼠标和键盘的位置(动作信息),并将其解析为纯粹的动作信息
 * 例如,主机器上鼠标位置(12,19)-->(12,20),解析的动作为 {id:up;num:1}
 */
public class DeviceMotionConvert implements Runnable {
    private static Logger log4j = Logger.getLogger(DeviceMotionConvert.class);

    ActionContainer motionContainer, serverActionContainer;
    ConcurrentHashMap<String, SystemInfo> clientMap;
    int clientScreenWidth, clientScreenHeight;
    int clientMouseX, clientMouseY;


    DeviceMotionConvert(ActionContainer motionContainer,
                        ActionContainer serverActionContainer,
                        ConcurrentHashMap<String, SystemInfo> clientMap) {
        this.motionContainer = motionContainer;
        this.serverActionContainer = serverActionContainer;
        this.clientMap = clientMap;

        SystemInfo clientSysInfo = clientMap.get("client");
        this.clientScreenWidth = clientSysInfo.getOsScreenWidth();
        this.clientScreenHeight = clientSysInfo.getOsSCreenHeight();
    }

    @Override
    public void run() {
        while (true) {
            if (!motionContainer.isEmpty()) {
                try {
                    JSONObject actionStr = motionContainer.poll();
                    String id = actionStr.getString("id");
                    /**
                     *具体每个判断对应的情况,参考 about.md
                     */
                    if ("1".equals(id)) {
                        //鼠标点击次数?  不知道怎么处理,暂不处理
                    } else if ("2".equals(id)||"3".equals(id)||"6".equals(id)
                            ||"7".equals(id)||"8".equals(id)) {
                        serverActionContainer.offer(actionStr);
                    } else if ("4".equals(id)) {
                        
                    } else if ("5".equals(id)) {
                        //鼠标拖拽,暂不处理
                    } else if ("9".equals(id)) {
                        //键盘键入,不知道怎么处理 暂不处理
                    } else {
                        log4j.warn("meaningless motion conversion.");
                    }
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    log4j.error("convert motion failed.");
                }
            }
        }
    }
}
