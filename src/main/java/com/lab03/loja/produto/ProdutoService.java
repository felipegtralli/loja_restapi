package com.lab03.loja.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab03.loja.itens.ItensRepository;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {
    private ProdutoRepository produtoRepository;
    private ItensRepository itensRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, ItensRepository itensRepository) {
        this.produtoRepository = produtoRepository;
        this.itensRepository = itensRepository;
    }

    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    public Produto getProduto(long id) {
        return produtoRepository.findById(id).get();
    }

    public void addProduto(Produto produto) {
        produtoRepository.save(produto);
    }

    public boolean produtoExists(long id) {
        return produtoRepository.existsById(id);
    }

    @Transactional
    public void updateValorProduto(long id, double valor) {
        getProduto(id).setValor(valor);
    }

    public void deleteProduto(long id) {
        Produto produto = produtoRepository.findById(id).get();
        itensRepository.deleteAll(itensRepository.findAllByProduto(produto).get());
        produtoRepository.delete(produto);
    }
}
