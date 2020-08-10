package com.savstanis.quickauction.exceptions;

public class UserAlreadyExistAuthenticationException  extends Exception {
    public UserAlreadyExistAuthenticationException(String message) {
        super(message);
    }
}
