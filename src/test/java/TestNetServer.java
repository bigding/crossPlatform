import net.Client;
import net.Server;

public class TestNetServer {
    public static void main(String[] args) {
        Server server = Server.getServer();
        server.startServer();
    }
}
