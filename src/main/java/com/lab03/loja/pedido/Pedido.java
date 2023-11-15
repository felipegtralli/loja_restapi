package com.lab03.loja.pedido;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lab03.loja.carrinho.Carrinho;
import com.lab03.loja.cliente.Cliente;
import com.lab03.loja.itens.Itens;

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

    @Column(columnDefinition="DATETIME DEFAULT NOW()", insertable=false)
    private LocalDate dataPedido;
    private double valorTotal;
    @Column(columnDefinition="VARCHAR(255) DEFAULT 'Aguardando Pagamento...'", insertable=false)
    private String statusPedido;
    private String Pagamento;
    @Column(columnDefinition="TEXT")
    private String itens;
    @Column(columnDefinition="TEXT")
    private String endereco;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_carrinho", nullable=false)
    private Carrinho carrinho;

    @ManyToOne
    @JoinColumn(name="id_cliente", nullable=false)
    private Cliente cliente;

    public Pedido() {}

    public Pedido(String Pagamento, Carrinho carrinho, Cliente cliente) {
        this.Pagamento = Pagamento;
        this.carrinho = carrinho;
        this.cliente = cliente;
        this.addItens();
        this.calcValorPedido();
        this.setEndereco();
    }

    public Pedido(long id, String itens, LocalDate dataPedido, double valorTotal, String statusPedido, String Pagamento, Carrinho carrinho, Cliente cliente) {
        this.id = id;
        this.itens = itens;
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
        this.statusPedido = statusPedido;
        this.Pagamento = Pagamento;
        this.carrinho = carrinho;
        this.cliente = cliente;
    }

    public void addItens() {
        Set<Itens> setItens = this.getCarrinho().getItens();
        this.itens = "[";
        for (Itens item : setItens) {
            String produtos = "{" +
            "id_produto='" + item.getProduto().getId() + "'" +            
            ",modelo='" + item.getProduto().getModelo() + "'" +
            ",marca='" + item.getProduto().getMarca() + "'" +
            ",tamanho='" + item.getProduto().getTamanho() + "'" +
            ",quantidade='" + item.getQuantidade() + "'" +
            "}";
            this.itens = this.itens.concat(produtos);
        }
        this.itens = this.itens.concat("]");
    }

    public void calcValorPedido() {
        Set<Itens> itens = this.getCarrinho().getItens();
        double valor = 0;
        for (Itens item : itens) {
            valor += (item.getProduto().getValor()) * item.getQuantidade(); 
        }
        this.valorTotal = valor;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItens() {
        return this.itens;
    }

    public void setItens(String itens) {
        this.itens = itens;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco() {
        this.endereco = this.getCliente().getEndereco().toString();
    }

    public LocalDate getDataPedido() {
        return this.dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public double getValorTotal() {
        return this.valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
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
            ", itens='" + getItens() + "'" +
            ", dataPedido='" + getDataPedido() + "'" +
            ", valor='" + getValorTotal() + "'" +
            ", statusPedido='" + getStatusPedido() + "'" +
            ", Pagamento='" + getPagamento() + "'" +
            ", carrinho='" + getCarrinho() + "'" +
            ", cliente='" + getCliente() + "'" +
            "}";
    }
}
