package com.boot.mytt.core.encrypt;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

/**
 * @author renwq
 * @date 2020/5/25
 */
@Slf4j
public class Base64Handler {

    private Base64Handler() {

    }

    private static class InnerClass {
        private static Base64Handler base64Handler = new Base64Handler();
    }

    public static Base64Handler getInstance() {
        return InnerClass.base64Handler;
    }

    /**
     * base64 编码
     *
     * @param data
     * @return
     */
    public String encode(String data) {
        return new String(encode(data.getBytes()));
    }

    /**
     * base64 编码
     *
     * @param data
     * @return
     */
    public byte[] encode(byte[] data) {
        return Base64.encodeBase64(data);
    }

    /**
     * base64 解码
     *
     * @param data
     * @return
     */
    public String decode(String data) {
        return new String(decode(data.getBytes()));
    }

    /**
     * base64 解码
     *
     * @param data
     * @return
     */
    public byte[] decode(byte[] data) {
        return Base64.decodeBase64(data);
    }
}
