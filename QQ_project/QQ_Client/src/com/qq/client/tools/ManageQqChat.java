/*
    这是一个管理用户聊天界面的类
 */
package com.qq.client.tools;

import com.qq.client.view.QqChat;

import java.util.HashMap;
import com.qq.client.view.*;

public class ManageQqChat {

    private static HashMap hm = new HashMap<String, QqChat>();

    //加入
    public static void addQqChat(String loginIdAndFriendId,QqChat qqChat){
        hm.put(loginIdAndFriendId, qqChat);
    }

    //取出
    public static QqChat getQqChat(String loginIndAndFriend){
        return (QqChat)hm.get(loginIndAndFriend);
    }
}
