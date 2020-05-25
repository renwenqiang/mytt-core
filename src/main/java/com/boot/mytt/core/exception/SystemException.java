package com.boot.mytt.core.exception;

/**
 * @author renwq
 * @date 2020/5/25
 */
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = 2642988980495842781L;

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
