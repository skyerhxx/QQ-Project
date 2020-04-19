/*
    这是客户端和服务器端保持通信的线程
 */
package com.qq.client.tools;

import com.qq.common.*;
import java.io.*;
import java.net.*;
public class ClientConServerThread extends Thread{

    private Socket s;

    public ClientConServerThread(Socket s){
        this.s = s;
    }

    public Socket getS() {
        return s;
    }

    @Override
    public void run() {
        while(true){
            //不停地读取从服务器端发来的消息
            try {
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                Message m = (Message)ois.readObject();
                System.out.println("读取到从服务器发来的消息"+m.getSender()+" 给 "+m.getGetter()+" 内容"+m.getCon());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
