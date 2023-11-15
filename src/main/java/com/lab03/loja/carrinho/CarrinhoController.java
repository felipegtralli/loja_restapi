package com.lab03.loja.carrinho;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lab03.loja.exception.ClienteNotFoundException;
import com.lab03.loja.exception.EmptyCarrinhoException;
import com.lab03.loja.exception.ProdutoInCarrinhoException;
import com.lab03.loja.exception.ProdutoNotFoundException;
import com.lab03.loja.produto.ProdutoService;

@RestController
public class CarrinhoController {
    private CarrinhoService carrinhoService;
    private ProdutoService produtoService;

    @Autowired
    public CarrinhoController(CarrinhoService carrinhoService, ProdutoService produtoService) {
        this.carrinhoService = carrinhoService;
        this.produtoService = produtoService;
    }    

    @GetMapping(path="api/clientes/{id_cliente}/carrinho") // retorna carrinho do cliente
    public Carrinho getCarrinho(@PathVariable long id_cliente) {
        try {
            return carrinhoService.getCarrinhoByCliente(id_cliente); // 200 OK
        }
        catch(NoSuchElementException ex) {
            throw new ClienteNotFoundException("cliente id=" + id_cliente + " not found"); // 404 se cliente nao existe
        }
    }

    @PostMapping(path="api/clientes/{id_cliente}/carrinho") // add produto no carrinho recebendo query param: ?id_produto=?&quantidade=?
    public ResponseEntity<Carrinho> addProdutoCarrinho(@PathVariable long id_cliente, @RequestParam long id_produto, @RequestParam int quantidade) {
        try {
            if(!produtoService.produtoExists(id_produto)) throw new ProdutoNotFoundException("produto id=" + id_produto + " not found"); // 404 se produto nao existe
            
            Carrinho carrinho = carrinhoService.getCarrinhoByCliente(id_cliente);
            if(carrinhoService.produtoExistsInCarrinho(id_produto, carrinho))
                             throw new ProdutoInCarrinhoException("produto id=" + id_produto + " is present in carrinho id=" + carrinho.getId()); // 409 se produto ja presente no carrinho
            
            carrinhoService.addProduto(carrinho, id_produto, quantidade);
            return ResponseEntity.created(null).build(); // retorna 201 se criado
        }
        catch(NoSuchElementException ex) {
            throw new ClienteNotFoundException("cliente id=" + id_cliente + " not found"); // 404 se cliente nao existe
        }
    }
    
    @PatchMapping(path="api/clientes/{id_cliente}/carrinho") // remove o item do carrinho recebendo query param: ?id_produto=?
    public ResponseEntity<Carrinho> removeItem(@PathVariable long id_cliente, @RequestParam long id_produto) {
        try {
            if(carrinhoService.carrinhoIsEmpty(id_cliente)) throw new EmptyCarrinhoException("carrinho is empty"); // 400 se carrinho vazio    
            if(!produtoService.produtoExists(id_produto)) throw new ProdutoNotFoundException("produto id=" + id_produto + " not found"); // 404 se produto nao existe

            return carrinhoService.removeItem(id_cliente, id_produto); // 200 se removido // 204 se item nao presente
        }
        catch(NoSuchElementException ex) {
            throw new ClienteNotFoundException("cliente id=" + id_cliente + " not found"); // 404 se cliente nao existe
        }
    }

    @PatchMapping(path="api/clientes/{id_cliente}/carrinho/{id_produto}") // atualiza a quantidade do produto; recebe query param: ?quantidade=?
    public void updateQuantidadeProduto(@PathVariable long id_cliente, @PathVariable long id_produto, @RequestParam int quantidade) {
        try {
            if(!produtoService.produtoExists(id_produto)) throw new ProdutoNotFoundException("produto id=" + id_produto + " not found"); // 404 se produto nao existe

            if(!carrinhoService.updateQuantidadeProduto(id_cliente, id_produto, quantidade)) // 200 se atualizado // 404 se produto nao presente no carrinho
                                throw new ProdutoNotFoundException("produto id=" + id_produto + " not found in carrinho id=" + getCarrinho(id_cliente).getId());

        }
        catch(NoSuchElementException ex) {
            throw new ClienteNotFoundException("cliente id=" + id_cliente + " not found"); // 404 se cliente nao existe
        }
    }

    @DeleteMapping(path="api/clientes/{id_cliente}/carrinho") // esvazia itens do carrinho
    public void esvaziaCarrinho(@PathVariable long id_cliente) {
        try {
            carrinhoService.esvaziaCarrinho(getCarrinho(id_cliente)); // 200 se removido
        }
        catch(NoSuchElementException ex) {
            throw new ClienteNotFoundException("cliente id=" + id_cliente + " not found"); // 404 se cliente nao existe
        }
    }
}
