package com.boot.mytt.core.encrypt.key;

import com.boot.mytt.core.exception.EncryptException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author renwq
 * @date 2020/5/25
 */
public class RSAKey implements Serializable {

    private static final String RSA_ALGORITHM = "RSA";
    private static final long serialVersionUID = 6945764375419518118L;

    private String publicKey;

    private String privateKey;

    private RSAPublicKey rsaPublicKey;

    private RSAPrivateKey rsaPrivateKey;

    public RSAKey(String privateKey, String publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;


        if (StringUtils.isNotEmpty(publicKey)) {
            try {
                KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
                X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
                rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
            } catch (Exception e) {
                throw new EncryptException("RSA get publicKey fail", e);
            }

        }

        if (StringUtils.isNotEmpty(privateKey)) {
            try {
                KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
                PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
                rsaPrivateKey = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
            } catch (Exception e) {
                throw new EncryptException("RSA get privateKey fail", e);
            }
        }
    }

    /**
     * get rsa public key
     *
     * @return String
     */
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * get private key
     *
     * @return String
     */
    public String getPrivateKey() {
        return privateKey;
    }

    /**
     * get rsa public key
     *
     * @return RSAPublicKey
     */
    public RSAPublicKey getRSAPublicKey() {
        return rsaPublicKey;
    }

    /**
     * get rsa private key
     *
     * @return RSAPrivateKey
     */
    public RSAPrivateKey getRSAPrivateKey() {
        return rsaPrivateKey;
    }
}
