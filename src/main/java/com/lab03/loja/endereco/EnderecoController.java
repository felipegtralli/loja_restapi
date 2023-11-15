package com.lab03.loja.endereco;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lab03.loja.exception.ClienteNotFoundException;

@RestController
public class EnderecoController {
    private EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping(path="/api/clientes/{id_cliente}/endereco") // retorna endereco do cliente
    public Endereco getEndereco(@PathVariable long id_cliente) {
        try{
            return enderecoService.getEndereco(id_cliente); // 200 OK
        }
        catch(NoSuchElementException ex){
            throw new ClienteNotFoundException("cliente id=" + id_cliente + " not found"); // 404 se cliente nao existe
        }
    }

    @PutMapping(path="/api/clientes/{id_cliente}/endereco") // atualiza endereco recebendo json body
    public void updateEndereco(@PathVariable long id_cliente, @RequestBody Endereco endereco) {
        try{
            enderecoService.updateEndereco(id_cliente, endereco); // 200 se atualizado
        }
        catch(NoSuchElementException ex){
            throw new ClienteNotFoundException("cliente id=" + id_cliente + " not found"); // 404 se cliente nao existe
        }
    }

    @DeleteMapping(path="/api/clientes/{id_cliente}/endereco") // apaga todos os campos do endereco
    public void esvaziaEndereco(@PathVariable long id_cliente) {
        try {
            enderecoService.esvaziaEndereco(id_cliente); // 200 se apagado
        }
        catch(NoSuchElementException ex){
            throw new ClienteNotFoundException("cliente id=" + id_cliente + " not found"); // 404 se cliente nao existe
        }
    }
}
