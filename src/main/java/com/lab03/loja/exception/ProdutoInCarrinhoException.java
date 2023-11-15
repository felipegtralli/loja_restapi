package com.lab03.loja.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT)
public class ProdutoInCarrinhoException extends RuntimeException{
    public ProdutoInCarrinhoException(String msg) {
        super(msg);
    }
}
