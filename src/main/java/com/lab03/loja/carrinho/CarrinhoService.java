package com.lab03.loja.carrinho;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lab03.loja.cliente.Cliente;
import com.lab03.loja.cliente.ClienteRepository;
import com.lab03.loja.itens.Itens;
import com.lab03.loja.itens.ItensRepository;
import com.lab03.loja.produto.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class CarrinhoService {
    private CarrinhoRepository carrinhoRepository;    
    private ClienteRepository clienteRepository;
    private ProdutoRepository produtoRepository;
    private ItensRepository itensRepository;

    @Autowired
    public CarrinhoService(CarrinhoRepository carrinhoRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository, ItensRepository itensRepository) {
        this.carrinhoRepository = carrinhoRepository;        
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.itensRepository = itensRepository;
    }

    public void addCarrinho(Cliente cliente) {
        carrinhoRepository.save(new Carrinho(cliente));
    }

    public void addProduto(Carrinho carrinho, long id, int quantidade) {
        itensRepository.save(new Itens(carrinho, produtoRepository.findById(id).get(), quantidade));
    }

    public Carrinho getCarrinhoByCliente(long id) {
        return carrinhoRepository.findByCliente(clienteRepository.findById(id).get()).get();
    }

    @Transactional
    public void esvaziaCarrinho(Carrinho carrinho) {
        itensRepository.deleteAllByCarrinho(carrinho);
        carrinho.setItens(null);
    }

    @Transactional
    public void deleteCarrinhoByCliente(long id) {
        Carrinho carrinho = getCarrinhoByCliente(id);
        esvaziaCarrinho(carrinho);
        carrinhoRepository.deleteById(carrinho.getId());
    }

    public boolean carrinhoIsEmpty(long id) {
        Set<Itens> itens = getCarrinhoByCliente(id).getItens();
        return itens.isEmpty();
    }

    @Transactional
    public ResponseEntity<Carrinho> removeItem(long id_cliente, long id_produto) {
        Set<Itens> itens = itensRepository.findAllByCarrinho(getCarrinhoByCliente(id_cliente)).get();
        for (Itens item : itens) {
            if(item.getProduto().getId() == id_produto) {
                itensRepository.delete(item);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.noContent().build();
    }

    @Transactional
    public boolean updateQuantidadeProduto(long id_cliente, long id_produto, int quantidade) {
        Set<Itens> itens = itensRepository.findAllByCarrinho(getCarrinhoByCliente(id_cliente)).get();
        for (Itens item : itens) {
            if(item.getProduto().getId() == id_produto) {
                item.setQuantidade(quantidade);
                return true;
            }
        }
        return false;
    }

    public boolean produtoExistsInCarrinho(long id_produto, Carrinho carrinho) {
        Set<Itens> itens = itensRepository.findAllByCarrinho(carrinho).get();
        
        return itens.stream().anyMatch(item -> item.getProduto().getId() == id_produto);
    }
}
