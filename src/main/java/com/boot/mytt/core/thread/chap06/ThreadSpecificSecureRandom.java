package com.boot.mytt.core.thread.chap06;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author renwq
 * @date 2021/3/20 22:55
 */
public enum  ThreadSpecificSecureRandom {

    INSTANCE;

    final static ThreadLocal<SecureRandom> SECURE_RANDOM = new ThreadLocal<SecureRandom>(){
        @Override
        protected SecureRandom initialValue() {
            SecureRandom secureRandom;
            try {
                secureRandom = SecureRandom.getInstance("SHA1PRNG");
            } catch (NoSuchAlgorithmException e) {
                secureRandom = new SecureRandom();
                throw new RuntimeException("No sha1prng algorithm able for this.");
            }
            secureRandom.nextBytes(new byte[20]);
            return secureRandom;
        }
    };

    public int nextInt(int upperBound) {
        SecureRandom secureRandom = SECURE_RANDOM.get();
        return secureRandom.nextInt(upperBound);
    }
}
