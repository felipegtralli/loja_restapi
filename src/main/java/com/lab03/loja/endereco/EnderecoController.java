package com.lab03.loja.endereco;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lab03.loja.exception.ClienteNotFoundException;

@RestController
public class EnderecoController {
    private EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping(path="/api/clientes/{id}/endereco")
    public Endereco getEndereco(@PathVariable long id) {
        try{
            Endereco endereco = enderecoService.getEndereco(id);
            return endereco;
        }
        catch(NoSuchElementException ex){
            throw new ClienteNotFoundException("cliente id=" + id + " not found");
        }
    }
}
