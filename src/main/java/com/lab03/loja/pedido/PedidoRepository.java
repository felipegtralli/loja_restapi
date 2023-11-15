package com.lab03.loja.pedido;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab03.loja.cliente.Cliente;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    public Optional<List<Pedido>> findAllByCliente(Cliente cliente); 
}
