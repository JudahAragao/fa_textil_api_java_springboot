package com.fatextil.service.exceptions;

import java.io.Serial;

public class ConstraintException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public ConstraintException(String msg) {
        super(msg);
    }
    public ConstraintException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
