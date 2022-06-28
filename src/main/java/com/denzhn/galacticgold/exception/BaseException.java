package com.denzhn.galacticgold.exception;

public abstract class BaseException extends RuntimeException{
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
