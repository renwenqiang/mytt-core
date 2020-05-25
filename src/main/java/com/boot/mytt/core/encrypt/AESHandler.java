package com.boot.mytt.core.encrypt;

import com.boot.mytt.core.exception.EncryptException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @author renwq
 * @date 2020/5/25
 */
@Slf4j
public class AESHandler {

    private Hex hex = new Hex();

    private AESHandler() {

    }

    private static class InnerClass {
        private static AESHandler aesHandler = new AESHandler();
    }

    public static AESHandler getInstance() {
        return InnerClass.aesHandler;
    }

    /**
     * AES encrypt
     *
     * @param publicKey
     * @param data
     * @return
     */
    public String encrypt(String publicKey, String data) {
        return new String(hex.encode(encrypt(publicKey, data.getBytes(StandardCharsets.UTF_8))));
    }

    /**
     * AES encrypt
     *
     * @param publicKey
     * @param data
     * @return
     */
    public byte[] encrypt(String publicKey, byte[] data) {
        try {
            byte[] raw = publicKey.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new EncryptException("AES encrypt fail", e);
        }
    }

    /**
     * AES decrypt
     *
     * @param publicKey
     * @param data
     * @return
     */
    public String decrypt(String publicKey, String data) {
        try {
            return new String(decrypt(publicKey, hex.decode(data.getBytes(StandardCharsets.UTF_8))));
        } catch (Exception e) {
            throw new EncryptException("AES decrypt fail", e);
        }
    }

    /**
     * AES decrypt
     *
     * @param publicKey
     * @param data
     * @return
     */
    public byte[] decrypt(String publicKey, byte[] data) {
        try {
            byte[] raw = publicKey.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new EncryptException("AES decrypt fail", e);
        }
    }

}
