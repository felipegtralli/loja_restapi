package com.lab03.loja.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class ProdutoNotFoundException extends RuntimeException{
    public ProdutoNotFoundException(String msg){
        super(msg);
    }
}
