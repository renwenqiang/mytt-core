package com.boot.mytt.core.encrypt.key;


import java.io.Serializable;

/**
 * @author renwq
 * @date 2020/5/25
 */
public class AESKey implements Serializable {

    private static final long serialVersionUID = -1857552769671388072L;
    private String key;

    public AESKey(String key) {
        this.key = key;
    }

    /**
     * get key
     *
     * @return
     */
    public String getKey() {
        return key;
    }
}
