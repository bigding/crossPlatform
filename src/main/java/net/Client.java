package net;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class Client {
    Socket client;
    PrintWriter pw;
    String msg = null;

    public Client() throws UnknownHostException, IOException {
        Socket client = new Socket("127.0.0.1", 7777);
        client.setSoTimeout(10000);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        PrintStream out = new PrintStream(client.getOutputStream());
        BufferedReader buf =  new BufferedReader(new InputStreamReader(client.getInputStream()));
        boolean flag = true;
        while(flag){
            System.out.print("输入信息：");
            String str = input.readLine();
            out.println(str);
            if("bye".equals(str)){
                flag = false;
            }else{
                try{
                    String echo = buf.readLine();
                    System.out.println(echo);
                }catch(SocketTimeoutException e){
                    System.out.println("Time out, No response");
                }
            }
        }
        input.close();
        if(client != null){
            client.close();
        }
    }
}
