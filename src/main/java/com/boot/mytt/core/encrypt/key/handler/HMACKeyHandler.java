package com.boot.mytt.core.encrypt.key.handler;


import com.boot.mytt.core.encrypt.MD5Handler;
import com.boot.mytt.core.encrypt.key.HMACKey;
import com.boot.mytt.core.exception.EncryptException;
import com.boot.mytt.core.util.RandomUtils;

/**
 * @author renwq
 * @date 2020/5/25
 */
public class HMACKeyHandler {

    private HMACKeyHandler() {

    }

    private static class InnerClass {
        private static HMACKeyHandler hmacKeyHandler = new HMACKeyHandler();
    }

    public static HMACKeyHandler getInstance() {
        return InnerClass.hmacKeyHandler;
    }

    /**
     * create key
     *
     * @return
     */
    public HMACKey createKey() {
        try {
            return new HMACKey(MD5Handler.getInstance().encrypt(RandomUtils.getRandomString(16, 64)));
        } catch (Exception e) {
            throw new EncryptException("HMAC create key fail", e);
        }
    }
}
