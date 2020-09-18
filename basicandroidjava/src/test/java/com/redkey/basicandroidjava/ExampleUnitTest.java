package com.redkey.basicandroidjava;

import com.redkey.basicandroidjava.lambda.impt.MessageFormatImpl;
import com.redkey.basicandroidjava.lambda.impt.UserCredentialImpl;
import com.redkey.basicandroidjava.lambda.inf.IMessageFormat;
import com.redkey.basicandroidjava.lambda.inf.IUserCredential;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void userTest() {
        IUserCredential ic = new UserCredentialImpl();
        System.out.println(ic.verifyUser("admin"));
        System.out.println(ic.getCredential("manager"));


//        匿名内部类，实现接口的抽象方法
        IUserCredential ic2 = new IUserCredential() {
            @Override
            public String verifyUser(String userName) {
                return "-----" + userName;
            }
        };

        System.out.println(ic2.getCredential("kook"));

//       Lambda表达式 实现接口的抽象方法
        IUserCredential ic3 = (String username)->{
            return  "admin".equals(username) ? "lbd管理员" : "lbd会员";
        };

        System.out.println(ic3.verifyUser("admin"));
    }

    @Test
    public void messageTest() {
        String msg = "kook";
        if (IMessageFormat.verifyMessage(msg)) {
            IMessageFormat format = new MessageFormatImpl();
            System.out.println(format.format("Hello", "Json"));
            System.out.println(IMessageFormat.verifyMessage("kook"));
        }
    }
}