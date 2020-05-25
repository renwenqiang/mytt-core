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
public class MD5Handler {

    private Hex hex = new Hex();

    private MD5Handler() {

    }

    private static class InnerClass {
        private static MD5Handler md5Handler = new MD5Handler();
    }

    public static MD5Handler getInstance() {
        return InnerClass.md5Handler;
    }

    /**
     * MD5 encrypt
     *
     * @param data
     * @return
     */
    public String encrypt(String data) {
        return new String(hex.encode(encrypt(data.getBytes())));
    }

    /**
     * MD5 encrypt
     *
     * @param data
     * @return
     */
    public byte[] encrypt(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return md.digest(data);
        } catch (Exception e) {
            throw new EncryptException("MD5 encrypt fail", e);
        }
    }
}
