package com.boot.mytt.core.encrypt.key;


import java.io.Serializable;

/**
 * @author renwq
 * @date 2020/5/25
 */
public class HMACKey implements Serializable {

    private static final long serialVersionUID = 5241616507590224281L;
    private String key;

    public HMACKey(String key) {
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
