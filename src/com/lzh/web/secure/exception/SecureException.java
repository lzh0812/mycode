package com.lzh.web.secure.exception;

@SuppressWarnings("serial")
public class SecureException extends RuntimeException {
    public SecureException() {
    }

    public SecureException(Throwable ex) {
        super(ex);
    }

    public SecureException(String msg) {
        super(msg);
    }

    public SecureException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
