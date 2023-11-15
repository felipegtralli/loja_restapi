package com.lab03.loja.produto;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @GetMapping(path="api/produtos") // lista todos os produtos
    public List<Produto> getAllProdutos() {
        return produtoService.getAllProdutos(); // 200 OK
    }

    @PostMapping(path="/api/produtos") // cria produto recebendo json body
    public ResponseEntity<Produto> addProduto(@RequestBody Produto produto) {
        produtoService.addProduto(produto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id_produto}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(location).build(); // 201 se criado
    }

    @GetMapping(path="/api/produtos/{id_produto}") // lista informacao do produto
    public Produto getProduto(@PathVariable long id_produto) {
        try{
            return produtoService.getProduto(id_produto); // 200 OK
        }
        catch(NoSuchElementException ex){
            throw new ProdutoNotFoundException("produto id=" + id_produto + " not found"); // 404 se produto nao existe
        }
    }

    @PatchMapping(path="/api/produtos/{id_produto}") // atualiza valor do produto recebendo query param ?valor=?
    public void updateValorProduto(@PathVariable long id_produto, double valor) {
        try {
            produtoService.updateValorProduto(id_produto, valor); // 201 se atualizado
        }
        catch(NoSuchElementException ex){
            throw new ProdutoNotFoundException("produto id=" + id_produto + " not found"); // 404 se produto nao existe
        }
    }

    @DeleteMapping(path="/api/produtos/{id_produto}") // deleta produto e objetos relacionados
    public void deleteProduto(@PathVariable long id_produto) {
        try {
            produtoService.deleteProduto(id_produto); // 200 se apagado
        }
        catch(NoSuchElementException ex){
            throw new ProdutoNotFoundException("produto id=" + id_produto + " not found"); // 404 se produto nao existe
        }
    }
}
