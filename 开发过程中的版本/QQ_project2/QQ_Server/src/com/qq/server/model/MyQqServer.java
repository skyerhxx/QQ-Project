/*
    这是qq服务器，它在监听，等待某个qq客户端，来连接
 */
package com.qq.server.model;
import com.qq.common.*;
import java.net.*;
import java.io.*;
import java.util.*;


public class MyQqServer {

    public MyQqServer(){

        try {
            //在9999监听
            System.out.println("我是服务器，在9999监听");
            ServerSocket ss = new ServerSocket(9999);
            //阻塞，等待连接
            while(true) {
                Socket s = ss.accept();

                //接收客户端发来的信息
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                User u = (User) ois.readObject();
                System.out.println("服务器接收到用户id:"+u.getUserId()+"   密码:"+u.getPasswd());
                Message m = new Message();
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                if (u.getPasswd().equals("123456")) {
                    //返回一个成功登录的信息包
                    m.setMesType("1");
                    oos.writeObject(m);
                } else {
                    m.setMesType("2");
                    oos.writeObject(m);
                    //关闭连接
                    s.close();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

    }

}
