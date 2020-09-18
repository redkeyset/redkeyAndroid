package com.redkey.basicandroidjava.lambda.inf;

public interface IUserCredential {
    String verifyUser(String userName);

    default String getCredential(String userName) {
        if ("admin".equals(userName)) {
            return userName + " --- 系统管理员";
        } else if ("manager".equals(userName)) {
            return userName + " --- 用户管理员";
        }
        return userName + " --- 普通用户";
    }
}
