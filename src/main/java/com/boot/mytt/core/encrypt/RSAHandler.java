package com.boot.mytt.core.encrypt;

import com.boot.mytt.core.exception.EncryptException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * @author renwq
 * @date 2020/5/25
 */
@Slf4j
public class RSAHandler {

    public static final String RSA_ALGORITHM = "RSA";

    private Hex hex = new Hex();

    private RSAHandler() {

    }

    private static class InnerClass {
        private static RSAHandler rsaHandler = new RSAHandler();
    }

    public static RSAHandler getInstance() {
        return InnerClass.rsaHandler;
    }

    /**
     * private encrypt
     *
     * @param rsaPrivateKey
     * @param data
     * @return
     */
    public String encrypt(RSAPrivateKey rsaPrivateKey, String data) {
        return new String(encrypt(rsaPrivateKey, data.getBytes()));
    }

    /**
     * private encrypt
     *
     * @param rsaPrivateKey
     * @param data
     * @return
     */
    public byte[] encrypt(RSAPrivateKey rsaPrivateKey, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, rsaPrivateKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data, rsaPrivateKey.getModulus().bitLength())).getBytes();
        } catch (Exception e) {
            throw new EncryptException("RSA encrypt fail", e);
        }
    }

    /**
     * private decrypt
     *
     * @param rsaPrivateKey
     * @param data
     * @return
     */
    public String decrypt(RSAPrivateKey rsaPrivateKey, String data) {
        return new String(decrypt(rsaPrivateKey, data.getBytes()));
    }

    /**
     * private decrypt
     *
     * @param rsaPrivateKey
     * @param data
     * @return
     */
    public byte[] decrypt(RSAPrivateKey rsaPrivateKey, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
            //拆分解密再组合为字符串
            return rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), rsaPrivateKey.getModulus().bitLength());
        } catch (Exception e) {
            throw new EncryptException("RSA decrypt fail", e);
        }
    }

    /**
     * public encrypt
     *
     * @param rsaPublicKey
     * @param data
     * @return
     */
    public String encrypt(RSAPublicKey rsaPublicKey, String data) {
        return new String(encrypt(rsaPublicKey, data.getBytes()));
    }

    /**
     * public entrypt
     *
     * @param rsaPublicKey
     * @param data
     * @return
     */
    public byte[] encrypt(RSAPublicKey rsaPublicKey, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data, rsaPublicKey.getModulus().bitLength())).getBytes();
        } catch (Exception e) {
            throw new EncryptException("RSA encrypt fail", e);
        }
    }

    /**
     * public decrypt
     *
     * @param rsaPublicKey
     * @param data
     * @return
     */
    public String decrypt(RSAPublicKey rsaPublicKey, String data) {
        return new String(decrypt(rsaPublicKey, data.getBytes()));
    }

    /**
     * public decrypt
     *
     * @param rsaPublicKey
     * @param data
     * @return
     */
    public byte[] decrypt(RSAPublicKey rsaPublicKey, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, rsaPublicKey);
            return rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), rsaPublicKey.getModulus().bitLength());
        } catch (Exception e) {
            throw new EncryptException("RSA decrypt fail", e);
        }
    }

    /**
     * 生成数字签名
     *
     * @param rsaPrivateKey
     * @param data
     * @return
     */
    public String sign(RSAPrivateKey rsaPrivateKey, String data) {
        return new String(hex.encode(sign(rsaPrivateKey, data.getBytes())));
    }

    /**
     * 生成数字签名
     *
     * @param rsaPrivateKey
     * @param data
     * @return
     */
    public byte[] sign(RSAPrivateKey rsaPrivateKey, byte[] data) {
        try {
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initSign(rsaPrivateKey);
            signature.update(data);
            return signature.sign();
        } catch (Exception e) {
            throw new EncryptException("RSA sign fail", e);
        }
    }

    /**
     * 校验数字签名
     *
     * @param rsaPublicKey
     * @param data
     * @param sign
     * @return
     */
    public Boolean verify(RSAPublicKey rsaPublicKey, String data, String sign) {
        return verify(rsaPublicKey, data.getBytes(), sign);
    }

    /**
     * 校验数字签名
     *
     * @param rsaPublicKey
     * @param data
     * @param sign
     * @return
     */
    public Boolean verify(RSAPublicKey rsaPublicKey, byte[] data, String sign) {
        try {
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initVerify(rsaPublicKey);
            signature.update(data);
            return signature.verify(hex.decode(sign.getBytes()));
        } catch (Exception e) {
            throw new EncryptException("RSA verify fail", e);
        }
    }

    /**
     * @param cipher
     * @param opmode
     * @param datas
     * @param keySize
     * @return
     */
    private byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize) {
        try {
            int maxBlock = 0;
            if (opmode == Cipher.DECRYPT_MODE) {
                maxBlock = keySize / 8;
            } else {
                maxBlock = keySize / 8 - 11;
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] buff;
            int i = 0;
            while (datas.length > offSet) {
                if (datas.length - offSet > maxBlock) {
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
            byte[] resultDatas = out.toByteArray();
            IOUtils.closeQuietly(out);
            return resultDatas;
        } catch (Exception e) {
            throw new EncryptException("RSA split code fail", e);
        }
    }
}
