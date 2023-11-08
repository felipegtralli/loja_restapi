package com.lab03.loja.endereco;

import com.lab03.loja.cliente.Cliente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
public class Endereco {
    @Id
    @Column(name="id_cliente")
    private long id;

    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String complemento;

    @OneToOne
    @MapsId
    @JoinColumn(name="id_cliente")
    private Cliente cliente;


    public Endereco() {
    }

    public Endereco(long id, String cep, String rua, String bairro, String cidade, String estado, String complemento, Cliente cliente) {
        this.id = id;
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.complemento = complemento;
        this.cliente = cliente;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return this.rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
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
            ", cep='" + getCep() + "'" +
            ", rua='" + getRua() + "'" +
            ", bairro='" + getBairro() + "'" +
            ", cidade='" + getCidade() + "'" +
            ", estado='" + getEstado() + "'" +
            ", complemento='" + getComplemento() + "'" +
            ", cliente='" + getCliente() + "'" +
            "}";
    }
}
