package com.redkey.basicandroidjava.lambda.inf;

@FunctionalInterface
public interface IMessageFormat {
    String format(String message, String format);

    String toString();

    static boolean verifyMessage(String msg) {
        if (msg != null) {
            return true;
        }
        return false;
    }
}
