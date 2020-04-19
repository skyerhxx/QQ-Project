/*
    这是与好友聊天的界面
 */
package com.qq.client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QqChat extends JFrame {

    JTextArea jta;
    JTextField jtf;
    JButton jb;
    JPanel jp;

    public static void main(String[] args) {
        QqChat qqChat = new QqChat("1");
    }

    public QqChat(String friend){
        jta = new JTextArea();
        jtf = new JTextField(15);
        jb = new JButton("发送");
        jp = new JPanel();
        jp.add(jtf);
        jp.add(jb);

        this.add(jta,"Center");
        this.add(jp,"South");
        this.setTitle("你正在和 "+friend+" 聊天");
        this.setIconImage(new ImageIcon("./src/resources/qq.gif").getImage());
        this.setSize(300,200);
        this.setVisible(true);
    }
}
