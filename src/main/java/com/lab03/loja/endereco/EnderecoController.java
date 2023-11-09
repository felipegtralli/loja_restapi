package com.lab03.loja.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnderecoController {
    private EnderecoService service;

    @Autowired
    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    @GetMapping(path="/api/clientes/{id}/endereco")
    public Endereco getEndereco(@PathVariable long id) {
        return service.getEndereco(id);
    }
}
