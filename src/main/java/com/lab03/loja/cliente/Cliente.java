package com.lab03.loja.cliente;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lab03.loja.carrinho.Carrinho;
import com.lab03.loja.endereco.Endereco;
import com.lab03.loja.pedido.Pedido;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cliente")
    private long id;

    private String nome;
    private String cpf;
    private String email;
    private String senha;

    @JsonIgnore
    @OneToOne(mappedBy="cliente", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Endereco endereco;
    
    @JsonIgnore
    @OneToOne(mappedBy="cliente")
    private Carrinho carrinho;

    @JsonIgnore
    @OneToMany(mappedBy="cliente")
    private Set<Pedido> pedido;


    public Cliente() {}

    public Cliente(String nome, String cpf, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public Cliente(long id, String nome, String cpf, String email, String senha, Endereco endereco, Carrinho carrinho, Set<Pedido> pedido) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
        this.carrinho = carrinho;
        this.pedido = pedido;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Carrinho getCarrinho() {
        return this.carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public Set<Pedido> getPedido() {
        return this.pedido;
    }

    public void setPedido(Set<Pedido> pedido) {
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", cpf='" + getCpf() + "'" +
            ", email='" + getEmail() + "'" +
            ", senha='" + getSenha() + "'" +
            ", endereco='" + getEndereco() + "'" +
            ", carrinho='" + getCarrinho() + "'" +
            ", pedido='" + getPedido() + "'" +
            "}";
    }    
}
