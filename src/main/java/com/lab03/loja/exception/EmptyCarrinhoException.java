package com.lab03.loja.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class EmptyCarrinhoException extends RuntimeException{
    public EmptyCarrinhoException(String msg) {
        super(msg);
    }
}