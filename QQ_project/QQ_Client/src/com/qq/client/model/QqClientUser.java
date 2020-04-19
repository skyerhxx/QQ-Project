/*
    多出这个model可能现在看不出其作用来，而如果有注册等功能的话有它在这里处理就会很方便
 */
package com.qq.client.model;

import com.qq.common.*;
public class QqClientUser {

    public boolean checkUser(User u){
        return new QqClientConServer().sendLoginInfoToServer(u);
    }
}
