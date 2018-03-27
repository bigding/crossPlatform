package net;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    public Server() throws IOException{
        serverSocket = new ServerSocket(7777);
        while(true) {
            Socket client = serverSocket.accept();
            System.out.println("连接成功");
            Thread serverThread = new Thread(new ServerThread(client));
            serverThread.start();
        }
    }
}
