/*
    功能: 是服务器和某个客户端的通信线程
 */

package com.qq.server.model;

import com.qq.common.*;
import java.net.*;
import java.io.*;

public class SerConClientThread extends Thread{

    Socket s;

    public  SerConClientThread(Socket s){
        //把服务器和该客户端的连接赋给s
        this.s = s;

    }

    @Override
    public void run() {
        while (true){
            //这里该线程就可以接收客户端的信息
            try {
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                Message m = (Message)ois.readObject();

                //System.out.println(m.getSender()+" 给 "+m.getGetter()+"说: "+m.getCon());

                //对从客户端取得的消息进行类型判断，然后做相应的处理
                if(m.getMesType().equals(MessageType.message_comm_mes)) {
                    //一会完成转发任务
                    //取得接收人的通信线程
                    SerConClientThread sc = ManageClientThread.getClientThread(m.getGetter());
                    ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
                    oos.writeObject(m);
                }else if(m.getMesType().equals(MessageType.message_get_onLineFriend))
                {
                    //把在服务器的好友给该客户端返回
                    String res = ManageClientThread.getAllOnLineUserId();
                    Message m2 = new Message();
                    m2.setMesType(MessageType.message_ret_onLineFriend);
                    m2.setCon(res);
                    m2.setGetter(m.getSender());
                    ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                    oos.writeObject(m2);

                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
