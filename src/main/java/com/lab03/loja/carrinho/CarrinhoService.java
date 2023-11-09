package com.lab03.loja.carrinho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab03.loja.cliente.Cliente;
import com.lab03.loja.cliente.ClienteRepository;

@Service
public class CarrinhoService {
    private CarrinhoRepository carrinhoRepository;    
    private ClienteRepository clienteRepository;


    @Autowired
    public CarrinhoService(CarrinhoRepository carrinhoRepository, ClienteRepository clienteRepository) {
        this.carrinhoRepository = carrinhoRepository;        
        this.clienteRepository = clienteRepository;
    }

    public void addCarrinho(Cliente cliente) {
        Carrinho carrinho = new Carrinho(cliente);
        carrinhoRepository.save(carrinho);
    }

    public Carrinho getCarrinho(long id) {
        return carrinhoRepository.findByCliente(clienteRepository.findById(id).get()).get();
    }


}
