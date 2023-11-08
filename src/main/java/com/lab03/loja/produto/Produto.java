package com.lab03.loja.produto;

import com.lab03.loja.carrinho.Carrinho;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_produto")
    private long id;
    
    private String modelo;
    private String marca;
    @Column(columnDefinition="varchar(5)")
    private String tamanho;

    @OneToOne(mappedBy="produto")
    private Carrinho carrinho;
    
    public Produto() {}
    

    public Produto(long id, String modelo, String marca, String tamanho, Carrinho carrinho) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.tamanho = tamanho;
        this.carrinho = carrinho;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTamanho() {
        return this.tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public Carrinho getCarrinho() {
        return this.carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", modelo='" + getModelo() + "'" +
            ", marca='" + getMarca() + "'" +
            ", tamanho='" + getTamanho() + "'" +
            ", carrinho='" + getCarrinho() + "'" +
            "}";
    }
}

