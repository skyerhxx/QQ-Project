/*
    定义包的种类
 */
package com.qq.common;

public interface MessageType {

    String message_succeed = "1";//表明是登录成功
    String message_login_fail = "2";//表明登录失败
    String message_comm_mes = "3"; //普通信息包
    String message_get_onLineFriend = "4";//要求在线好友的包
    String message_ret_onLineFriend = "5";//返回在线好友的包


}
