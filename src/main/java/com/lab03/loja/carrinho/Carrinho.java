package com.lab03.loja.carrinho;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lab03.loja.cliente.Cliente;
import com.lab03.loja.pedido.Pedido;
import com.lab03.loja.produto.Produto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_carrinho")
    private long id;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id_cliente", referencedColumnName="id_cliente")
    private Cliente cliente;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="itens",
    joinColumns = 
        {@JoinColumn(name="id_carrinho", referencedColumnName="id_carrinho")},
      inverseJoinColumns = 
        {@JoinColumn(name="id_produto", referencedColumnName="id_produto")})
    private List<Produto> produtos;

    @JsonIgnore
    @OneToMany(mappedBy="carrinho")
    private Set<Pedido> pedido;

  public Carrinho() {
  }

  public Carrinho(Cliente cliente) {
    this.cliente = cliente;
  }

  public Carrinho(long id, Cliente cliente, List<Produto> produtos, Set<Pedido> pedido) {
    this.id = id;
    this.cliente = cliente;
    this.produtos = produtos;
    this.pedido = pedido;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Cliente getCliente() {
    return this.cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public List<Produto> getProdutos() {
    return this.produtos;
  }

  public void setProduto(List<Produto> produtos) {
    this.produtos = produtos;
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
      ", cliente='" + getCliente() + "'" +
      ", produto='" + getProdutos() + "'" +
      ", pedido='" + getPedido() + "'" +
      "}";
  }
}
