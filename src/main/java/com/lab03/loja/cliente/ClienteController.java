package com.lab03.loja.cliente;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lab03.loja.exception.ClienteNotFoundException;

@RestController
public class ClienteController {
    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService service) {
        this.clienteService = service;
    }

    @GetMapping(path="/api/clientes")
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }
    
    @PostMapping(path="/api/clientes")
    public ResponseEntity<Cliente> addCliente(@RequestBody Cliente cliente) {
        clienteService.addCliente(cliente);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path="/api/clientes/{id}")
    public Cliente getCliente(@PathVariable long id) {
        try{
            Cliente cliente = clienteService.getCliente(id);
            return cliente;
        }
        catch(NoSuchElementException ex){
            throw new ClienteNotFoundException("cliente id=" + id + " not found");
        }
    }
}
