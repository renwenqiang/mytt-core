package com.boot.mytt.core.encrypt;

import com.boot.mytt.core.exception.EncryptException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author renwq
 * @date 2020/5/25
 */
@Slf4j
public class HMACHandler {

    private Hex hex = new Hex();

    private HMACHandler() {

    }

    private static class InnerClass {
        private static HMACHandler hmacHandler = new HMACHandler();
    }

    public static HMACHandler getInstance() {
        return InnerClass.hmacHandler;
    }

    /**
     * HMAC encrypt
     *
     * @param key
     * @param data
     * @return
     */
    public String encrypt(String key, String data) {
        return new String(hex.encode(encrypt(key, data.getBytes())));
    }

    /**
     * HMAC encrypt
     *
     * @param key
     * @param data
     * @return
     */
    public byte[] encrypt(String key, byte[] data) {
        try {
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKey);
            return mac.doFinal(data);
        } catch (Exception e) {
            throw new EncryptException("HMAC encrypt fail", e);
        }
    }
}
