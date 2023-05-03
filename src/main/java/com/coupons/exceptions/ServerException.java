package com.coupons.exceptions;

import com.coupons.enums.ErrorType;

public class ServerException extends Exception {
    private ErrorType errorType;

    public ServerException(String message, ErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

    public ServerException(String message, Exception e, ErrorType errorType) {
        super(message, e);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
