package com.boot.mytt.core.exception;

public class Test01 {

    public String fn() {

        if (true) {
            throw new EncryptException("抛出 EncryptException");
        }
        return "fn";
    }
}
