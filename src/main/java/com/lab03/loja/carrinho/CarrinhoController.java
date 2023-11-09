package com.lab03.loja.carrinho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarrinhoController {
    private CarrinhoService service;

    @Autowired
    public CarrinhoController(CarrinhoService service) {
        this.service = service;
    }    

    @GetMapping(path="api/clientes/{id}/carrinho")
    public Carrinho getCarrinho(@PathVariable long id) {
        return service.getCarrinho(id);
    }
}
