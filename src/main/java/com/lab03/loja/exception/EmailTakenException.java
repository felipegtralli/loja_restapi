package com.lab03.loja.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT)
public class EmailTakenException extends RuntimeException{
    public EmailTakenException(String msg) {
        super(msg);
    }
}
