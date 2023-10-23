package com.fatextil.service.exceptions;

import java.io.Serial;

public class BusinessRuleException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public BusinessRuleException(String msg) {
        super(msg);
    }
    public BusinessRuleException(String msg, Throwable cause) {
        super(msg, cause);
    }
}