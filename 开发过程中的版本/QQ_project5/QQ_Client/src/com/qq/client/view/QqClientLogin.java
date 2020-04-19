/**
 * 功能: qq客户端登录界面
 */

package com.qq.client.view;

import com.qq.client.model.QqClientUser;
import com.qq.common.User;
import org.w3c.dom.ls.LSOutput;
import com.qq.client.tools.*;
import com.qq.common.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QqClientLogin extends JFrame implements ActionListener{

    //定义界面上部需要的组件
    JLabel jbl1;

    //定义界面中部需要的组件
        //中部有3个JPanel, 由一个选项卡窗口管理
    JTabbedPane jtp;
    JPanel jp2,jp3,jp4;
    JLabel jp2_jbl1,jp2_jbl2,jp2_jbl3,jp2_jbl4;
    JButton jp2_jb1;
    JTextField jp2_jtf;
    JPasswordField jp2_jpf;
    JCheckBox jp2_jcb1,jp2_jcb2;


    //定义界面下部需要的组件
    JPanel jp1;
    JButton jp1_jb1,jp1_jb2,jp1_jb3;

    public static void main(String[] args){
        QqClientLogin qqClientLogin = new QqClientLogin();

    }
    public QqClientLogin(){
        //处理界面上部
        jbl1 = new JLabel(new ImageIcon("./src/resources/tou.gif"));
        //处理界面中部
        jp2 = new JPanel(new GridLayout(3,3));

        jp2_jbl1 = new JLabel("QQ号码",JLabel.CENTER);
        jp2_jbl2 = new JLabel("QQ密码",JLabel.CENTER);
        jp2_jbl3 = new JLabel("忘记密码",JLabel.CENTER);
        jp2_jbl3.setForeground(Color.blue);
        jp2_jbl4 = new JLabel("申请密码保护",JLabel.CENTER);
        jp2_jb1 = new JButton(new ImageIcon("./src/resources/clear.gif"));
        jp2_jtf = new JTextField();
        jp2_jpf = new JPasswordField();
        jp2_jcb1 = new JCheckBox("隐身登录");
        jp2_jcb2 = new JCheckBox("记住密码");
        //把控件按照顺序加入到jp2
        jp2.add(jp2_jbl1);
        jp2.add(jp2_jtf);
        jp2.add(jp2_jb1);
        jp2.add(jp2_jbl2);
        jp2.add(jp2_jpf);
        jp2.add(jp2_jbl3);
        jp2.add(jp2_jcb1);
        jp2.add(jp2_jcb2);
        jp2.add(jp2_jbl4);
        //创建选项卡窗口
        jtp = new JTabbedPane();
        jtp.add("QQ号码",jp2);
        jp3 = new JPanel();
        jtp.add("手机号码",jp3);
        jp4 = new JPanel();
        jtp.add("电子邮件",jp4);


        //处理界面下部
        jp1 = new JPanel();
        jp1_jb1 = new JButton(new ImageIcon("./src/resources/denglu.gif"));
        //响应用户点击登录
        jp1_jb1.addActionListener(this);
        jp1_jb2 = new JButton(new ImageIcon("./src/resources/quxiao.gif"));
        jp1_jb3 = new JButton(new ImageIcon("./src/resources/xiangdao.gif"));
        //把3个按钮放入到jp1
        jp1.add(jp1_jb1);
        jp1.add(jp1_jb2);
        jp1.add(jp1_jb3);


        this.add(jbl1,"North");
        this.add(jtp,"Center");
        //把jp1放在页面下部
        this.add(jp1,"South");
        this.setSize(350,240);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true); //设置让它可见

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        //如果用户点击登录     就去连接MyQqClientConServer
        if(arg0.getSource()==jp1_jb1){
            QqClientUser qqClientUser = new QqClientUser();
            User u = new User();
            u.setUserId(jp2_jtf.getText().trim());
            u.setPasswd(new String(jp2_jpf.getPassword()));

            if(qqClientUser.checkUser(u)){
                //发送一个要求返回在线好友的请求包
                try {
                    //把创建好友列表的语句提前
                    QqFriendList qqList = new QqFriendList(u.getUserId());
                    ManageQqFriendList.addQqFriendList(u.getUserId(),qqList);

                    //发送一个要求返回在线好友的请求包
                    ObjectOutputStream oos = new ObjectOutputStream
                            (ManageClientConServerThread.getClientConServerThread(u.getUserId()).getS().getOutputStream());

                    //做一个Message
                    Message m = new Message();
                    m.setMesType(MessageType.message_get_onLineFriend);
                    //指明我要的是这个qq号的好友情况
                    m.setSender(u.getUserId());
                    oos.writeObject(m);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                //关闭掉登录界面
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this,"用户名密码错误");
            }
        }

    }
}
