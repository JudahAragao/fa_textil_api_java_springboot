package com.fatextil.rest.controller.exceptions;

import javax.naming.AuthenticationException;

public class CustomAuthenticationException extends AuthenticationException {

    public CustomAuthenticationException(String message) {
        super(message);
    }

}
