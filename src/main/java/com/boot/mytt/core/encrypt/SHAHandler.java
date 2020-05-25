package com.boot.mytt.core.encrypt;

import com.boot.mytt.core.exception.EncryptException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;

/**
 * @author renwq
 * @date 2020/5/25
 */
@Slf4j
public class SHAHandler {

    private Hex hex = new Hex();

    private SHAHandler() {

    }

    private static class InnerClass {
        private static SHAHandler shaHandler = new SHAHandler();
    }

    public static SHAHandler getInstance() {
        return InnerClass.shaHandler;
    }

    /**
     * SHA encrypt
     *
     * @param data
     * @return
     */
    public String encrypt(String data) {
        return new String(hex.encode(encrypt(data.getBytes())));
    }

    /**
     * SHA encrypt
     *
     * @param data
     * @return
     */
    public byte[] encrypt(byte[] data) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA");
            sha.update(data);
            return sha.digest();
        } catch (Exception e) {
            throw new EncryptException("SHA encrypt fail", e);
        }
    }
}
