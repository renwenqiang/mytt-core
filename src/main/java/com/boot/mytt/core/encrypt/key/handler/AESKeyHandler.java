package com.boot.mytt.core.encrypt.key.handler;

import com.boot.mytt.core.encrypt.key.AESKey;
import com.boot.mytt.core.exception.EncryptException;
import com.boot.mytt.core.util.RandomUtils;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.KeyGenerator;
import java.security.SecureRandom;

/**
 * @author renwq
 * @date 2020/5/25
 */
public class AESKeyHandler {

    private Hex hex = new Hex();

    private AESKeyHandler() {

    }

    private static class InnerClass {
        private static AESKeyHandler aesKeyHandler = new AESKeyHandler();
    }

    public static AESKeyHandler getInstance() {
        return InnerClass.aesKeyHandler;
    }

    /**
     * create key
     *
     * @return
     */
    public AESKey createKey() {
        try {
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            keygen.init(128, new SecureRandom(RandomUtils.getRandomString(16, 64).getBytes()));
            return new AESKey(new String(hex.encode(keygen.generateKey().getEncoded())));
        } catch (Exception e) {
            throw new EncryptException("AES create key fail", e);
        }
    }
}
