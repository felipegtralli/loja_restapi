package com.lab03.loja.carrinho;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab03.loja.cliente.Cliente;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long>{
    public Optional<Carrinho> findByCliente(Cliente cliente); 
}
