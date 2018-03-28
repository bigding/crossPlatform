import net.Client;
import net.Server;

public class TestNetClient {
    public static void main(String[] args) {
        Client client = Client.getClient();
        client.startClient();
    }
}
