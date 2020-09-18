package com.redkey.basicandroidjava.lambda.impt;

import com.redkey.basicandroidjava.lambda.inf.IUserCredential;

public class UserCredentialImpl implements IUserCredential {

    @Override
    public String verifyUser(String userName) {
        if (userName.equals("admin")){
            return "系统管理员";
        }else if (userName.equals("manager")){
            return "用户管理员";
        }else {
            return "普通用户";
        }
    }
}
