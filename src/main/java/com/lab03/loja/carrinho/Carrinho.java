package com.lab03.loja.carrinho;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lab03.loja.cliente.Cliente;
import com.lab03.loja.itens.Itens;
import com.lab03.loja.pedido.Pedido;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Carrinho {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_carrinho")
    private long id;

    @JsonIgnore
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id_cliente", referencedColumnName="id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy="carrinho")
    private Set<Itens> itens;

    @JsonIgnore
    @OneToMany(mappedBy="carrinho")
    private Set<Pedido> pedido;

  public Carrinho() {
  }

  public Carrinho(Cliente cliente) {
    this.cliente = cliente;
  }

  public Carrinho(long id, Cliente cliente, Set<Itens> itens, Set<Pedido> pedido) {
    this.id = id;
    this.cliente = cliente;
    this.itens = itens;
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

  public Set<Itens> getItens() {
    return this.itens;
  }

  public void setItens(Set<Itens> itens) {
    this.itens = itens;
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
      ", itens='" + getItens() + "'" +
      ", pedido='" + getPedido() + "'" +
      "}";
  }
}
