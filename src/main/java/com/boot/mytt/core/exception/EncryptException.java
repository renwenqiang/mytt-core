package com.boot.mytt.core.exception;

/**
 * encrypt exception
 *
 * @author wuxj
 */
public class EncryptException extends RuntimeException {

    private static final long serialVersionUID = -7496204240343442575L;

    public EncryptException(String message) {
        super(message);
    }

    public EncryptException(String message, Throwable cause) {
        super(message, cause);
    }
}
