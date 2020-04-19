/*
    这是客户端连接服务器的后台
 */
package com.qq.client.model;

import com.qq.common.*;

import com.qq.client.tools.*;
import java.util.*;
import java.net.*;
import java.io.*;
public class QqClientConServer {

    public Socket s;

    //发送第一次请求
    public boolean sendLoginInfoToServer(Object o){
        boolean b = false;
        try {
            s = new Socket("127.0.0.1",9999);
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(o);

            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

            Message ms = (Message)ois.readObject();
            //这里就是验证用户登录的地方
            if(ms.getMesType().equals("1")) {
                //就创建一个该qq号和服务器端保持通信连接的线程
                ClientConServerThread ccst = new ClientConServerThread(s);
                //启动该通信线程
                ccst.start();
                ManageClientConServerThread.addClientConServerThread(((User)o).getUserId(),ccst);
                b = true;
            }else{
                //关闭Socket
                s.close();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

        }
        return b;
    }
    public void SendInfoToServer(Object o){

    }

}
