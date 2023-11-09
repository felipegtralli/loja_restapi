package com.lab03.loja.carrinho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab03.loja.cliente.Cliente;
import com.lab03.loja.cliente.ClienteRepository;

@Service
public class CarrinhoService {
    private CarrinhoRepository repository;    
    private ClienteRepository clienteRepository;


    @Autowired
    public CarrinhoService(CarrinhoRepository repository, ClienteRepository clienteRepository) {
        this.repository = repository;        
        this.clienteRepository = clienteRepository;

    }

    public void addCarrinho(Cliente cliente) {
        Carrinho carrinho = new Carrinho(cliente);
        repository.save(carrinho);
    }

    public Carrinho getCarrinho(long id) {
        return repository.findByCliente(clienteRepository.findById(id).get()).get();
    }


}
