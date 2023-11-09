package com.lab03.loja.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        return ResponseEntity.created(null).build();
    }
}
