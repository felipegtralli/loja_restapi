package com.lab03.loja.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab03.loja.carrinho.CarrinhoService;
import com.lab03.loja.endereco.EnderecoService;
import com.lab03.loja.exception.EmailTakenException;
import com.lab03.loja.pedido.PedidoService;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
    private ClienteRepository clienteRepository;    
    private CarrinhoService carrinhoService;
    private EnderecoService enderecoService;
    private PedidoService pedidoService;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository,CarrinhoService carrinhoService, EnderecoService enderecoService, PedidoService pedidoService) {
        this.clienteRepository = clienteRepository;
        this.carrinhoService = carrinhoService;
        this.enderecoService = enderecoService;
        this.pedidoService = pedidoService;
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente getCliente(long id) {
        return clienteRepository.findById(id).get();
    }

    public void addCliente(Cliente cliente) {
        if(clienteRepository.existsByEmail(cliente.getEmail())) throw new EmailTakenException("email=" + cliente.getEmail() + " taken");

        clienteRepository.save(cliente);
        carrinhoService.addCarrinho(cliente);
        enderecoService.addEndereco(cliente);
    }

    public boolean clienteExists(long id) {
        return clienteRepository.existsById(id);
    }

    public void deleteCliente(long id) {
        pedidoService.deletePedidosByCliente(id);
        carrinhoService.deleteCarrinhoByCliente(id);
    }

    @Transactional
    public void updateCliente(long id, Cliente cliente) {
        if(clienteRepository.existsByEmail(cliente.getEmail())) throw new EmailTakenException("email=" + cliente.getEmail() + " taken");
        Cliente clienteAtual = clienteRepository.findById(id).get();

        clienteAtual.setAll(cliente);
    }
}
