package com.lab03.loja.pedido;

import java.time.LocalDate;

import com.lab03.loja.carrinho.Carrinho;
import com.lab03.loja.cliente.Cliente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_pedido")
    private long id;

    private String infoCliente;
    private String itens;
    private LocalDate dataPedido;
    private double valor;
    private String statusPedido;
    private String Pagamento;

    @ManyToOne
    @JoinColumn(name="id_carrinho", nullable=false)
    private Carrinho carrinho;

    @ManyToOne
    @JoinColumn(name="id_cliente", nullable=false)
    private Cliente cliente;

    public Pedido() {}

    public Pedido(long id, String infoCliente, String itens, LocalDate dataPedido, double valor, String statusPedido, String Pagamento, Carrinho carrinho, Cliente cliente) {
        this.id = id;
        this.infoCliente = infoCliente;
        this.itens = itens;
        this.dataPedido = dataPedido;
        this.valor = valor;
        this.statusPedido = statusPedido;
        this.Pagamento = Pagamento;
        this.carrinho = carrinho;
        this.cliente = cliente;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInfoCliente() {
        return this.infoCliente;
    }

    public void setInfoCliente(String infoCliente) {
        this.infoCliente = infoCliente;
    }

    public String getItens() {
        return this.itens;
    }

    public void setItens(String itens) {
        this.itens = itens;
    }

    public LocalDate getDataPedido() {
        return this.dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatusPedido() {
        return this.statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    public String getPagamento() {
        return this.Pagamento;
    }

    public void setPagamento(String Pagamento) {
        this.Pagamento = Pagamento;
    }

    public Carrinho getCarrinho() {
        return this.carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", infoCliente='" + getInfoCliente() + "'" +
            ", itens='" + getItens() + "'" +
            ", dataPedido='" + getDataPedido() + "'" +
            ", valor='" + getValor() + "'" +
            ", statusPedido='" + getStatusPedido() + "'" +
            ", Pagamento='" + getPagamento() + "'" +
            ", carrinho='" + getCarrinho() + "'" +
            ", cliente='" + getCliente() + "'" +
            "}";
    }
}
