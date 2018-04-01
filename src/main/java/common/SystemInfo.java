package common;

import org.apache.log4j.Logger;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.Properties;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * 查看系统信息,此类为单例模式
 */

public class SystemInfo {
    /**单例定义*/
    private static SystemInfo systemInfo = null;
    /**log4j定义*/
    private static Logger logger = Logger.getLogger(SystemInfo.class);
    /**获取屏幕信息需要的类*/
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    /**系统名*/
    private String osName;
    /**系统架构*/
    private String osArch ;
    /**系统版本号*/
    private String osVersion ;
    /**屏幕宽度*/
    private int osScreenWidth;
    /**屏幕高度*/
    private int osScreenHeight;
    /**系统IP*/
    private String osIp ;
    /**系统MAC地址*/
    private String osHac;
    /**系统时间*/
    private Date osDate;
    /**系统CPU个数*/
    private Integer osCpus ;
    /**系统用户名*/
    private String osUserName;
    /**用户的当前工作目录*/
    private String osUserDir;
    /**用户的主目录*/
    private String osUserHome;

    /**Java的运行环境版本*/
    private String javaVersion ;
    /**java默认的临时文件路径*/
    private String javaIoTmpdir;

    /**java 平台*/
    private String sunDesktop ;

    /**文件分隔符  在 unix 系统中是＂／＂*/
    private String fileSeparator;
    /**路径分隔符  在 unix 系统中是＂:＂*/
    private String pathSeparator;
    /**行分隔符   在 unix 系统中是＂/n＂*/
    private String lineSeparator;

    private SystemInfo() {
        init();
        logger.info("create os info success.");
    }

    public static SystemInfo getInstance(){
        if(systemInfo == null){
            synchronized(SystemInfo.class){
                if(systemInfo == null){
                    systemInfo =  new SystemInfo();
                }
            }
            return systemInfo;
        }
        else{
            return systemInfo;
        }
    }

    /**
     * 输出信息 
     */
    public void PrintInfo(){
        Properties props=System.getProperties();
        System.out.println("Java_version:"+props.getProperty("java.version"));
        System.out.println("default_file_path:"+props.getProperty("java.io.tmpdir"));
        System.out.println("os_name:"+props.getProperty("os.name"));
        System.out.println("os_architecture:"+props.getProperty("os.arch"));
        System.out.println("od_version:"+props.getProperty("os.version"));
        System.out.println("file_separator:"+props.getProperty("file.separator"));   //在 unix 系统中是＂／＂
        System.out.println("path_separator:"+props.getProperty("path.separator"));   //在 unix 系统中是＂:＂
        System.out.println("line_separator:"+props.getProperty("line.separator"));   //在 unix 系统中是＂/n＂
        System.out.println("user_name:"+props.getProperty("user.name"));
        System.out.println("user_home:"+props.getProperty("user.home"));
        System.out.println("user_directory:"+props.getProperty("user.dir"));
        System.out.println("screen_width:"+this.osScreenWidth);
        System.out.println("screen_height"+this.osScreenHeight);
    }

    /**
     * 初始化基本属性 
     */
    private void init(){
        Properties props=System.getProperties();

        this.javaVersion = props.getProperty("java.version");
        this.javaIoTmpdir = props.getProperty("java.io.tmpdir");
        this.osName = props.getProperty("os.name");
        this.osArch = props.getProperty("os.arch");
        this.osVersion = props.getProperty("os.version");
        this.fileSeparator = props.getProperty("file.separator");
        this.pathSeparator = props.getProperty("path.separator");
        this.lineSeparator = props.getProperty("line.separator");
        this.osScreenHeight = dim.height;
        this.osScreenWidth = dim.width;

        this.osUserName = props.getProperty("user.name");
        this.osUserHome = props.getProperty("user.home");
        this.osUserDir = props.getProperty("user.dir");

        this.sunDesktop = props.getProperty("sun.desktop");

        this.osDate = new Date();
        this.osCpus = Runtime.getRuntime().availableProcessors();

        try {
            ipMac();
        } catch (Exception e) {
            this.osIp = "";
            this.osHac = "";
        }
    }

    /**
     * 获取ip和mac地址 
     * @throws Exception
     */
    @SuppressWarnings("resource")
    private void ipMac() throws Exception{
        InetAddress address = InetAddress.getLocalHost();
        NetworkInterface ni = NetworkInterface.getByInetAddress(address);
        ni.getInetAddresses().nextElement().getAddress();
        byte[] mac = ni.getHardwareAddress();
        String sIP = address.getHostAddress();
        String sMAC = "";
        Formatter formatter = new Formatter();
        for (int i = 0; i < mac.length; i++) {
            sMAC = formatter.format(Locale.getDefault(), "%02X%s", mac[i],
                    (i < mac.length - 1) ? "-" : "").toString();

        }
        SystemInfo.this.osIp = sIP;
        SystemInfo.this.osHac = sMAC;
    }

    public String getOsName() {
        return osName;
    }

    public String getOsArch() {
        return osArch;
    }

    public String gOssVersion() {
        return osVersion;
    }

    public String getOsIp() {
        return osIp;
    }

    public String getOsHac() {
        return osHac;
    }

    public Date getOsDate() {
        return osDate;
    }

    public Integer getOsCpus() {
        return osCpus;
    }

    public String getOsUserName() {
        return osUserName;
    }

    public String getOsUserDir() {
        return osUserDir;
    }

    public String getOsUserHome() {
        return osUserHome;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public String getJavaIoTmpdir() {
        return javaIoTmpdir;
    }

    public String getSunDesktop() {
        return sunDesktop;
    }

    public String getFileSeparator() {
        return fileSeparator;
    }

    public String getPathSeparator() {
        return pathSeparator;
    }

    public String getLineSeparator() {
        return lineSeparator;
    }

    public int getOsScreenWidth() {
        return osScreenWidth;
    }

    public int getOsSCreenHeight() {
        return osScreenHeight;
    }
}  