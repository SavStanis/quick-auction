package com.savstanis.quickauction.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserAlreadyExistAuthenticationException  extends Exception {
    public UserAlreadyExistAuthenticationException(String message) {
        super(message);
    }
}
