package com.pinmost.api.common.exception;

/**
 * User: mei
 * Date: 3/29/16
 * Time: 12:58
 */
public class UnauthorizedException extends InternalException{
    public UnauthorizedException() {
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause);
    }
}
