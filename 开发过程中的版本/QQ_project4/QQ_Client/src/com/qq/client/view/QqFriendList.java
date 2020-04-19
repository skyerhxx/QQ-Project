/*
    我的好友列表，也包括陌生人和黑名单
 */
package com.qq.client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QqFriendList extends JFrame implements ActionListener,MouseListener{

    //处理第一张卡片（好友）
    //hy = 好友
    JPanel jpmsr1,jpmsr2,jpmsr3;
    JButton jpmsr_jb1,jpmsr_jb2,jpmsr_jb3;
    JScrollPane jsp2;
    String owner;

    //处理第二张卡片（陌生人）
    //msr = 陌生人
    JPanel jphy1,jphy2,jphy3;
    JButton jphy_jb1,jphy_jb2,jphy_jb3;
    JScrollPane jsp1;

    //把整个JFrame设置成CardLayout布局
    CardLayout cl;
    public static void main(String[] args) {
        //QqFriendList qqFriendList = new QqFriendList();
    }

    public QqFriendList(String ownerId){
        this.owner = ownerId;
        //处理第一张卡片(显示好友列表)
        jphy_jb1 = new JButton("我的好友");
        jphy_jb2 = new JButton("陌生人");
        jphy_jb2.addActionListener(this);
        jphy_jb3 = new JButton("黑名单");
        jphy1 = new JPanel(new BorderLayout());
        //假定有50个好友
        jphy2 = new JPanel(new GridLayout(50,1,4,4));
        //给jphy2初始化50个好友
        JLabel []jbls = new JLabel[50];
        for (int i = 0; i < jbls.length; i++) {
            jbls[i] = new JLabel(i+1+"", new ImageIcon("./src/resources/mm.jpg"),JLabel.LEFT);
            jbls[i].addMouseListener(this);
            jphy2.add(jbls[i]);

        }


        jphy3 = new JPanel(new GridLayout(2,1));
        //把两个按钮加入到jphy3
        jphy3.add(jphy_jb2);
        jphy3.add(jphy_jb3);



        jsp1 = new JScrollPane(jphy2);

        //对jphy1初始化
        jphy1.add(jphy_jb1,"North");
        jphy1.add(jsp1,"Center");
        jphy1.add(jphy3,"South");

        //处理第二张卡片
        jpmsr_jb1 = new JButton("我的好友");
        jpmsr_jb1.addActionListener(this);
        jpmsr_jb2 = new JButton("陌生人");
        jpmsr_jb3 = new JButton("黑名单");
        jpmsr1 = new JPanel(new BorderLayout());
        //假定有20个陌生人
        jpmsr2 = new JPanel(new GridLayout(20,1,4,4));
        //给jphy2初始化50个陌生人
        JLabel []jbls2 = new JLabel[20];
        for (int i = 0; i < jbls2.length; i++) {
            jbls2[i] = new JLabel(i+1+"", new ImageIcon("./src/resources/mm.jpg"),JLabel.LEFT);
            jpmsr2.add(jbls2[i]);
        }


        jpmsr3 = new JPanel(new GridLayout(2,1));
        //把两个按钮加入到jpmsr3
        jpmsr3.add(jpmsr_jb1);
        jpmsr3.add(jpmsr_jb2);


        jsp2 = new JScrollPane(jpmsr2);

        //对jpmsr1初始化
        jpmsr1.add(jpmsr3,"North");
        jpmsr1.add(jsp2,"Center");
        jpmsr1.add(jpmsr_jb3,"South");


        cl = new CardLayout();
        this.setLayout(cl);
        this.add(jphy1,"1");
        this.add(jpmsr1,"2");
        //在窗口显示自己的编号
        this.setTitle(ownerId);
        this.setSize(140,400);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        //如果用户点击了陌生人按钮,就显示第二张卡片
        if(arg0.getSource() == jphy_jb2){
            cl.show(this.getContentPane(),"2");
        }
        else if(arg0.getSource()==jpmsr_jb1){
            cl.show(this.getContentPane(),"1");
        }
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        //响应用户双击的事件，并得到好友的编号
        if(arg0.getClickCount() == 2){
            //得到该好友的编号
            String friendNo = ((JLabel)arg0.getSource()).getText();
            //System.out.println("你希望和 "+friendNo+" 聊天");
            QqChat qqChat = new QqChat(this.owner, friendNo);


        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        JLabel jl = (JLabel)arg0.getSource();
        jl.setForeground(Color.red);
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        JLabel jl = (JLabel)arg0.getSource();
        jl.setForeground(Color.black);
    }
}
