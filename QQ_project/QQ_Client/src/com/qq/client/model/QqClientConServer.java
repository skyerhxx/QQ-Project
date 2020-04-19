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

    //发送用户登录请求到服务器
    public boolean sendLoginInfoToServer(Object o){
        boolean b = false;

        try {
            s = new Socket("127.0.0.1",9999);
            //通过ObjectOutputStream给服务器发送对象
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            //o是User类型的，里面含着传过来的用户的账号和密码的信息
            oos.writeObject(o);

            //接受服务器传回来的
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


}
