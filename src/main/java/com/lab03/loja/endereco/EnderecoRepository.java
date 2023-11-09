package com.lab03.loja.endereco;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab03.loja.cliente.Cliente;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    public Optional<Endereco> findByCliente(Cliente cliente);
}
