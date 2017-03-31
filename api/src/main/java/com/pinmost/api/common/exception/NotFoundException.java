package com.pinmost.api.common.exception;

/**
 * User: mei
 * Date: 3/29/16
 * Time: 12:59
 */
public class NotFoundException extends InternalException{
    public NotFoundException() {
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }
}
