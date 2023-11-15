package com.lab03.loja.itens;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lab03.loja.carrinho.Carrinho;
import com.lab03.loja.produto.Produto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Itens {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_item")
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_carrinho")
    private Carrinho carrinho;

    @ManyToOne
    @JoinColumn(name="id_produto")
    private Produto produto;

    private int quantidade;

    public Itens() {
    }

    public Itens(Carrinho carrinho, Produto produto, int quantidade) {
        this.carrinho = carrinho;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Itens(long id, Carrinho carrinho, Produto produto, int quantidade) {
        this.id = id;
        this.carrinho = carrinho;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Carrinho getCarrinho() {
        return this.carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "{" +
            "produto='" + getProduto() + "'" +
            ", quantidade='" + getQuantidade() + "'" +
            "}";
    }

}
