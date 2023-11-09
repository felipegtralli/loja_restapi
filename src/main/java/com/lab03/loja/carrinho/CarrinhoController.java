package com.lab03.loja.carrinho;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lab03.loja.exception.ClienteNotFoundException;

@RestController
public class CarrinhoController {
    private CarrinhoService carrinhoService;

    @Autowired
    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }    

    @GetMapping(path="api/clientes/{id}/carrinho")
    public Carrinho getCarrinho(@PathVariable long id) {
        try{
            Carrinho carrinho = carrinhoService.getCarrinho(id);
            return carrinho;
        }
        catch(NoSuchElementException ex){
            throw new ClienteNotFoundException("cliente id=" + id + " not found");
        }
    }
}
