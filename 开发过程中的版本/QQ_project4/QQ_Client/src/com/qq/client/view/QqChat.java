/*
    这是与好友聊天的界面
    因为客户端要处于读取的状态，因此我们把他做成一个线程
 */
package com.qq.client.view;

import com.qq.client.model.*;
import com.qq.client.tools.*;
import com.qq.common.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.io.*;
public class QqChat extends JFrame implements ActionListener{

    JTextArea jta;
    JTextField jtf;
    JButton jb;
    JPanel jp;
    String ownerId;
    String friendId;

    public static void main(String[] args) {
        //QqChat qqChat = new QqChat("1");
    }

    public QqChat(String ownerId, String friend){
        this.ownerId = ownerId;
        this.friendId = friend;
        jta = new JTextArea();
        jtf = new JTextField(15);
        jb = new JButton("发送");
        jb.addActionListener(this);
        jp = new JPanel();
        jp.add(jtf);
        jp.add(jb);

        this.add(jta,"Center");
        this.add(jp,"South");
        this.setTitle(ownerId+" 正在和 "+friend+" 聊天");
        this.setIconImage(new ImageIcon("./src/resources/qq.gif").getImage());
        this.setSize(300,200);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getSource()==jb){
            //如果用户点击了发送按钮
            Message m = new Message();
            m.setSender(this.ownerId);
            m.setGetter(this.friendId);
            m.setCon(jtf.getText());
            m.setSendTime(new java.util.Date().toString());
            //发送给服务器
            try {
                ObjectOutputStream oos = new ObjectOutputStream(ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
                oos.writeObject(m);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

//    @Override
//    public void run() {
//        while(true){
//
//            try {
//                //读取(如果读不到就等待)
//                ObjectInputStream ois = new ObjectInputStream(QqClientConServer.s.getInputStream());
//                Message m = (Message) ois.readObject();
//                //显示
//                String info = m.getSender()+" 对 "+m.getGetter()+" 说:"+m.getCon()+"\r\n";
//                this.jta.append(info);
//
//
//            } catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
