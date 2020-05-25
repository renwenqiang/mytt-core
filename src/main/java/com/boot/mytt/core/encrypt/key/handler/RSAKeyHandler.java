package com.boot.mytt.core.encrypt.key.handler;

import com.boot.mytt.core.encrypt.key.RSAKey;
import com.boot.mytt.core.exception.EncryptException;
import org.apache.commons.codec.binary.Base64;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

/**
 * @author renwq
 * @date 2020/5/25
 */
public class RSAKeyHandler {

    private RSAKeyHandler() {

    }

    private static class InnerClass {
        private static RSAKeyHandler rsaKeyHandler = new RSAKeyHandler();
    }

    public static RSAKeyHandler getInstance() {
        return InnerClass.rsaKeyHandler;
    }

    /**
     * create key
     *
     * @param keySize
     * @return
     */
    public RSAKey createKeys(int keySize) {
        try {
            int size = keySize;
            if (size < 512) {
                size = 1024;
            }
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(size);
            KeyPair keyPair = kpg.generateKeyPair();
            String publicKeyStr = Base64.encodeBase64URLSafeString(keyPair.getPublic().getEncoded());
            String privateKeyStr = Base64.encodeBase64URLSafeString(keyPair.getPrivate().getEncoded());
            return new RSAKey(privateKeyStr, publicKeyStr);
        } catch (Exception e) {
            throw new EncryptException("RSA create key fail", e);
        }
    }
}
