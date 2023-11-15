package com.lab03.loja.itens;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab03.loja.carrinho.Carrinho;
import com.lab03.loja.produto.Produto;

@Repository
public interface ItensRepository extends JpaRepository<Itens, Long>{
    public void deleteAllByCarrinho(Carrinho carrinho);
    public Optional<Set<Itens>> findAllByCarrinho(Carrinho carrinho);
    public Optional<Set<Itens>> findAllByProduto(Produto produto);
}
