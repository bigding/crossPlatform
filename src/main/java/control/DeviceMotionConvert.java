package control;

import com.alibaba.fastjson.JSONObject;
import common.ActionContainer;
import common.SystemInfo;
import motionSimulation.MouseMotion;
import org.apache.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 读取鼠标和键盘的位置(动作信息),并将其解析为纯粹的动作信息
 * 例如,主机器上鼠标位置(12,19)-->(12,20),解析的动作为 {id:up;num:1}
 */
public class DeviceMotionConvert implements Runnable {
    private static Logger log4j = Logger.getLogger(DeviceMotionConvert.class);

    SystemInfo serverSysInfo = SystemInfo.getInstance();
    ActionContainer motionContainer, serverActionContainer;
    ConcurrentHashMap<String, SystemInfo> clientMap;
    int clientScreenWidth, clientScreenHeight;
    int serverScreenWidth, serverScreenHeight;
    int clientMouseX, clientMouseY;
    int serverMouseX, serverMouseY;


    public DeviceMotionConvert(ActionContainer motionContainer,
                               ActionContainer serverActionContainer,
                               ConcurrentHashMap<String, SystemInfo> clientMap) {
        this.motionContainer = motionContainer;
        this.serverActionContainer = serverActionContainer;
        this.clientMap = clientMap;

        SystemInfo clientSysInfo = clientMap.get("client");
        this.clientScreenWidth = clientSysInfo.getOsScreenWidth();
        this.clientScreenHeight = clientSysInfo.getOsSCreenHeight();

        this.serverScreenWidth = serverSysInfo.getOsScreenWidth();
        this.serverScreenHeight = serverSysInfo.getOsSCreenHeight();
    }

    //当鼠标位置在两台机器间变化时,纵坐标的变化值转换函数,posiY的转换为A->B
    public int posiYChange(int heightA,int heightB,int posiY){
        int diff = heightA - heightB;
        if(diff == 0)
            return posiY;
        else if(diff > 0){
            if(posiY <= diff/2){
                return 0;
            }
            else if(posiY >= diff/2 + heightB){
                return heightB;
            }
            else{
                return posiY - diff/2;
            }
        }
        else{
            return posiY + diff/2;
        }
    }

    @Override
    public void run() {
        log4j.info("motion convert class ready to work.");
        int mouseAt = 1;   //当为1时,鼠标在服务器机;当为2时,鼠标在客户机
        int[] mousePosi = new int[2];  //存储前一刻鼠标的位置
        while (true) {
            if (!motionContainer.isEmpty()) {
                try {
                    JSONObject actionStr = motionContainer.poll();
                    String id = actionStr.getString("id");
                    /**
                     *具体每个判断对应的情况,参考 about.md
                     */
                    if ("4".equals(id)) {
                        JSONObject mouse = actionStr;
                        serverMouseX = (int) mouse.get("posiX");
                        serverMouseY = (int) mouse.get("posiY");
                        //当鼠标到达服务器机最左端时,将鼠标交给客户机
                        if (mouseAt == 1) {
                            if (serverMouseX == 0) {
                                mouseAt = 2;
                                clientMouseX = clientScreenWidth;
                                clientMouseY = posiYChange(serverScreenHeight,clientScreenHeight,serverMouseY);

                                JSONObject jsonMouseMotion = new JSONObject();
                                jsonMouseMotion.put("id", "to");
                                jsonMouseMotion.put("posiX", clientMouseX);
                                jsonMouseMotion.put("posiY", clientMouseY);
                                serverActionContainer.offer(jsonMouseMotion);

                                mousePosi[0] = 2;
                                mousePosi[1] = serverMouseY;

                                MouseMotion.moveTo(mousePosi[0], mousePosi[1]);
                            }
                        }
                        else if(mouseAt == 2){
                            int x = serverMouseX - mousePosi[0];
                            int y = serverMouseY - mousePosi[1];

                            clientMouseX = clientMouseX + x;
                            clientMouseY = clientMouseY + y;
                            if(clientMouseY < 0) {
                                clientMouseY = 0;
                            }
                            if(clientMouseY > clientScreenHeight) {
                                clientMouseY = clientScreenHeight;
                            }
                            if(clientMouseX < 0) {
                                clientMouseX = 0;
                            }
                            if(clientMouseX > clientScreenWidth) {
                                clientMouseX = clientScreenWidth;
                            }

                            JSONObject jsonMouseMotion = new JSONObject();
                            jsonMouseMotion.put("id", "to");
                            jsonMouseMotion.put("posiX", clientMouseX);
                            jsonMouseMotion.put("posiY", clientMouseY);
                            serverActionContainer.offer(jsonMouseMotion);

                            if(clientMouseX != clientScreenWidth){
                                MouseMotion.moveTo(mousePosi[0], mousePosi[1]);
                            }
                            //鼠标到达客户端的最右端,鼠标移交给服务器机
                            else{
                                mouseAt = 1;
                                serverMouseX = 1;
                                serverMouseY = posiYChange(clientScreenHeight,serverScreenHeight,clientMouseY);
                                MouseMotion.moveTo(serverMouseX,serverMouseY);
                            }
                        }
                    }
                    //当鼠标光标正在客户机时,
                    if (mouseAt == 2) {
                        if ("1".equals(id)) {
                            //鼠标点击次数?  不知道怎么处理,暂不处理
                        } else if ("2".equals(id) || "3".equals(id) || "6".equals(id) ||
                                "7".equals(id) || "8".equals(id)) {
                            serverActionContainer.offer(actionStr);
                        } else if ("5".equals(id)) {
                            //鼠标拖拽,暂不处理
                        } else if ("9".equals(id)) {
                            //键盘键入,不知道怎么处理 暂不处理
                        } else {
                            log4j.warn("meaningless motion conversion.");
                        }
                    }
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    log4j.error("convert motion failed.");
                }
            }
        }
    }
}
