package com.redkey.lib_okhttp_red;

import okio.ByteString;

public class OkIoUtils {

    public static void main(String[] args) {

        String str = "This is Okio String 中国";
        System.out.println("str：" + str);

        ByteString byteString = ByteString.encodeUtf8(str);
        System.out.println("byteString:" + byteString);

        ByteString md5 = byteString.md5();
        System.out.println("md5:" + md5);

        ByteString sha1 = byteString.sha1();
        System.out.println("sha1:" + sha1);

        ByteString base64 = ByteString.decodeBase64("I am is 中国人");
        System.out.println("base64:" + base64);

//        String hex = base64.sha1().hex();
//        System.out.println("hex:" + hex);


    }
}
