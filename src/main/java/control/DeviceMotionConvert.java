package control;

import com.alibaba.fastjson.JSONObject;
import common.ActionContainer;
import common.SystemInfo;
import hook.HookControl;
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
    ActionContainer mouseMoveContainer, serverActionContainer;
    ConcurrentHashMap<String, SystemInfo> clientMap;
    int clientScreenWidth, clientScreenHeight;
    int serverScreenWidth, serverScreenHeight;
    int clientMouseX, clientMouseY;
    int serverMouseX, serverMouseY;
    HookControl hookControl;


    public DeviceMotionConvert(ActionContainer mouseMoveContainer,
                               ActionContainer serverActionContainer,
                               ConcurrentHashMap<String, SystemInfo> clientMap) {
        this.mouseMoveContainer = mouseMoveContainer;
        this.serverActionContainer = serverActionContainer;
        this.clientMap = clientMap;

        SystemInfo clientSysInfo = clientMap.get("client");
        this.clientScreenWidth = clientSysInfo.getOsScreenWidth();
        this.clientScreenHeight = clientSysInfo.getOsSCreenHeight();

        this.serverScreenWidth = serverSysInfo.getOsScreenWidth();
        this.serverScreenHeight = serverSysInfo.getOsSCreenHeight();

        //初始化HookControl
        hookControl = new HookControl(serverActionContainer);
    }

    //当鼠标位置在两台机器间变化时,纵坐标的变化值转换函数,posiY的转换为A->B
    public int posiYChange(int heightA, int heightB, int posiY) {
        int diff = heightA - heightB;
        if (diff == 0)
            return posiY;
        else if (diff > 0) {
            if (posiY <= diff / 2) {
                return 0;
            } else if (posiY >= diff / 2 + heightB) {
                return heightB;
            } else {
                return posiY - diff / 2;
            }
        } else {
            return posiY + diff / 2;
        }
    }

    @Override
    public void run() {
        log4j.info("motion convert class ready to work.");
        int mouseAt = 1;   //当为1时,鼠标在服务器机;当为2时,鼠标在客户机
        int[] mousePosi = new int[2];  //存储前一刻鼠标的位置
        while (true) {
            if (!mouseMoveContainer.isEmpty()) {
                try {
                    JSONObject action = mouseMoveContainer.poll();
                    String id = action.getString("id");
                    /**
                     *具体每个判断对应的情况,参考 about.md
                     */
                    if ("4".equals(id)) {
                        JSONObject mouse = action;
                        serverMouseX = (int) mouse.get("posiX");
                        serverMouseY = (int) mouse.get("posiY");
                        //当鼠标到达服务器机最左端时,将鼠标交给客户机
                        if (mouseAt == 1) {
                            if (serverMouseX == 0) {
                                //将部分监听权交给hook, 并将服务器机鼠标光标隐藏,禁用鼠标点击,禁用键盘输入
                                hookControl.startHook();
                                mouseAt = 2;
                                clientMouseX = clientScreenWidth -1;
                                clientMouseY = posiYChange(serverScreenHeight, clientScreenHeight, serverMouseY);

                                JSONObject jsonMouseMotion = new JSONObject();
                                jsonMouseMotion.put("id", "to");
                                jsonMouseMotion.put("posiX", clientMouseX);
                                jsonMouseMotion.put("posiY", clientMouseY);
                                serverActionContainer.offer(jsonMouseMotion);


                                //使光标位于服务器机屏幕中央
                                mousePosi[0] = serverScreenWidth/2;
                                mousePosi[1] = serverScreenHeight/2;
                                log4j.info("posi1:"+mousePosi[0]+"\t"+mousePosi[1]);
                                log4j.info("debug1:"+mouseAt+ "\t"+serverMouseX+"\t"+serverMouseY+
                                        "\t"+clientMouseX+"\t"+clientMouseY);
                                MouseMotion.moveTo(mousePosi[0], mousePosi[1]);
                            }
                        }
                        //鼠标的光标在客户机时,处理鼠标移动信息和到达边缘时光标所在处的变换
                        else if (mouseAt == 2) {
                            if(serverMouseX <= 0){
                                continue;
                            }

                            //下面对x，y值的判断是为了防止绝对值超过一百的情况
                            int x = serverMouseX - mousePosi[0];
                            if(x > 100){
                                x -= 100;
                            }
                            else if(x < -100){
                                x += 100;
                            }
                            int y = serverMouseY - mousePosi[1];
                            if(y > 100){
                                y -= 100;
                            }
                            else if(y < -100){
                                y += 100;
                            }

                            clientMouseX = clientMouseX + x;
                            clientMouseY = clientMouseY + y;
                            //客户机屏幕上边缘
                            if (clientMouseY < 0) {
                                clientMouseY = 0;
                            }
                            //客户机屏幕下边缘
                            if (clientMouseY > clientScreenHeight) {
                                clientMouseY = clientScreenHeight;
                            }
                            //客户机屏幕左边缘
                            if (clientMouseX < 0) {
                                clientMouseX = 0;
                            }
                            //客户机屏幕右边缘
                            if (clientMouseX > clientScreenWidth) {
                                clientMouseX = clientScreenWidth;
                            }
                            log4j.info("posi2:"+mousePosi[0]+"\t"+mousePosi[1]);
                            log4j.info("debug2:"+mouseAt+"\t"+x+"\t"+y + "\t"+serverMouseX+"\t"+
                                    serverMouseY+"\t"+clientMouseX+"\t"+clientMouseY);

                            JSONObject jsonMouseMotion = new JSONObject();
                            jsonMouseMotion.put("id", "to");
                            jsonMouseMotion.put("posiX", clientMouseX);
                            jsonMouseMotion.put("posiY", clientMouseY);
                            serverActionContainer.offer(jsonMouseMotion);

                            if (clientMouseX != clientScreenWidth) {
                                //鼠标在主机上的活动范围为以屏幕中心为中心，长度为200px的正方形
                                boolean status = false;
                                mousePosi[0] = serverMouseX;
                                mousePosi[1] = serverMouseY;
                                if(serverMouseX - serverScreenWidth/2 > 100){
                                    mousePosi[0] = serverMouseX - 100;
                                    status = true;
                                }
                                else if(serverMouseX - serverScreenWidth/2 < -100){
                                    mousePosi[0] = serverMouseX + 100;
                                    status = true;
                                }
                                if(serverMouseY - serverScreenHeight/2 > 100){
                                    mousePosi[1] = serverMouseY - 100;
                                    status = true;
                                }
                                else if(serverMouseY - serverScreenHeight/2 < -100){
                                    mousePosi[1] = serverMouseY + 100;
                                    status = true;
                                }
                                if(status){
                                    MouseMotion.moveTo(mousePosi[0], mousePosi[1]);
                                }
                            }
                            //鼠标到达客户端的最右端,鼠标移交给服务器机
                            else {
                                log4j.info("x is 1366?? "+clientMouseX+"\t"+ clientScreenWidth);
                                mouseAt = 1;
                                serverMouseX = 1;
                                serverMouseY = posiYChange(clientScreenHeight, serverScreenHeight, clientMouseY);
                                //显示服务器机鼠标光标并移动到指定位置
                                //同时回复正常监听
                                hookControl.stopHook();
                                MouseMotion.moveTo(serverMouseX, serverMouseY);
                                //初始化客户机鼠标位置,鼠标在客户机最右侧会影响后续判断
                                clientMouseY = 0;
                                clientMouseX = 0;
                            }
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
