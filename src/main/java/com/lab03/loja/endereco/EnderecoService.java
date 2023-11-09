package com.lab03.loja.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab03.loja.cliente.Cliente;
import com.lab03.loja.cliente.ClienteRepository;

@Service
public class EnderecoService {
    private EnderecoRepository repository;
    private ClienteRepository clienteRepository;

    @Autowired
    public EnderecoService(EnderecoRepository repository, ClienteRepository clienteRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
    }

    public Endereco getEndereco(long id) {
        return repository.findByCliente(clienteRepository.findById(id).get()).get();
    }

    public void addEndereco(Cliente cliente) {
        Endereco endereco = new Endereco(cliente);
        repository.save(endereco);
    }
}
