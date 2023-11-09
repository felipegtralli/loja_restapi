package com.lab03.loja.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab03.loja.carrinho.CarrinhoService;
import com.lab03.loja.endereco.EnderecoService;

@Service
public class ClienteService {
    private ClienteRepository clienteRepository;    
    private CarrinhoService carrinhoService;
    private EnderecoService enderecoService;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository,CarrinhoService carrinhoService, EnderecoService enderecoService) {
        this.clienteRepository = clienteRepository;
        this.carrinhoService = carrinhoService;
        this.enderecoService = enderecoService;
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public void addCliente(Cliente cliente) {
        clienteRepository.save(cliente);
        carrinhoService.addCarrinho(cliente);
        enderecoService.addEndereco(cliente);
    }
}
