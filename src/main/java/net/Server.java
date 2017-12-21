package net;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket socket;
    private ServerSocket serverSocket;
    public Server() throws IOException{
        serverSocket = new ServerSocket(7777);
        while (true){
            socket = serverSocket.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("you input is :" + br.readLine());
        }
    }

    public static void main(String[] args) {
        try {
            new Server();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
