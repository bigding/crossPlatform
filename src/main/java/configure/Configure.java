package configure;

public class Configure {
    int serverOrClient; // 1为服务器  2及以上为客户端
    String ip;
    int port;

    public boolean isServer(){
        if(serverOrClient == 1)
            return true;
        return false;
    }

    public boolean isClient(){
        if(serverOrClient > 1)
            return true;
        return false;
    }

    public int getServerOrClient() {
        return serverOrClient;
    }

    public void setServerOrClient(int serverOrClient) {
        this.serverOrClient = serverOrClient;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
