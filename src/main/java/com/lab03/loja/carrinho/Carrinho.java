package com.lab03.loja.carrinho;

import java.util.Set;

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

    @OneToOne(cascade=CascadeType.ALL)
    @JoinTable(name="itens",
    joinColumns = 
        {@JoinColumn(name="id_carrinho", referencedColumnName="id_carrinho")},
      inverseJoinColumns = 
        {@JoinColumn(name="id_produto", referencedColumnName="id_produto")})
    private Produto produto;

    @OneToMany(mappedBy="carrinho")
    private Set<Pedido> pedido;

  public Carrinho() {
  }

  public Carrinho(Cliente cliente) {
    this.cliente = cliente;
  }

  public Carrinho(long id, Cliente cliente, Produto produto, Set<Pedido> pedido) {
    this.id = id;
    this.cliente = cliente;
    this.produto = produto;
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

  public Produto getProduto() {
    return this.produto;
  }

  public void setProduto(Produto produto) {
    this.produto = produto;
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
      ", produto='" + getProduto() + "'" +
      ", pedido='" + getPedido() + "'" +
      "}";
  }
}
