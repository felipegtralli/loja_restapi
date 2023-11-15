package com.lab03.loja.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab03.loja.cliente.Cliente;
import com.lab03.loja.cliente.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class EnderecoService {
    private EnderecoRepository enderecoRepository;
    private ClienteRepository clienteRepository;

    @Autowired
    public EnderecoService(EnderecoRepository repository, ClienteRepository clienteRepository) {
        this.enderecoRepository = repository;
        this.clienteRepository = clienteRepository;
    }

    public Endereco getEndereco(long id) {
        return enderecoRepository.findByCliente(clienteRepository.findById(id).get()).get();
    }

    public void addEndereco(Cliente cliente) {
        enderecoRepository.save(new Endereco(cliente));
    }

    @Transactional
    public void updateEndereco(Long id, Endereco endereco) {
        getEndereco(id).setAll(endereco);
    }

    @Transactional
    public void esvaziaEndereco(Long id) {
        getEndereco(id).setAll(new Endereco());
    }
}
