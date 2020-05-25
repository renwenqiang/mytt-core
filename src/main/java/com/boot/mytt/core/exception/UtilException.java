package com.boot.mytt.core.exception;

/**
 * @author renwq
 * @date 2020/5/25
 */
public class UtilException extends RuntimeException {

    private static final long serialVersionUID = -7496204240343442575L;

    public UtilException(String message) {
        super(message);
    }

    public UtilException(String message, Throwable cause) {
        super(message, cause);
    }
}
