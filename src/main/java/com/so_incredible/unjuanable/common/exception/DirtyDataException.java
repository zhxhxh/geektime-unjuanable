package com.so_incredible.unjuanable.common.exception;

public class DirtyDataException extends RuntimeException{
    public DirtyDataException() {
    }

    public DirtyDataException(String message) {
        super(message);
    }

    public DirtyDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public DirtyDataException(Throwable cause) {
        super(cause);
    }

    public DirtyDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
