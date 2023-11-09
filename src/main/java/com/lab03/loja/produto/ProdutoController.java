package com.lab03.loja.produto;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lab03.loja.exception.ProdutoNotFoundException;

@RestController
public class ProdutoController {
    private ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping(path="api/produtos")
    public List<Produto> getAllProdutos() {
        return produtoService.getAllProdutos();
    }

    @PostMapping(path="/api/produtos")
    public ResponseEntity<Produto> addCliente(@RequestBody Produto produto) {
        produtoService.addProduto(produto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path="/api/produtos/{id}")
    public Produto getProduto(@PathVariable long id) {
        try{
            Produto produto = produtoService.getProduto(id);
            return produto;
        }
        catch(NoSuchElementException ex){
            throw new ProdutoNotFoundException("produto id=" + id + " not found");
        }
    }
}
