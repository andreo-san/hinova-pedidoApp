package com.hinova.PedidoApp.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String dscPedido;

    @Column(nullable = false)
    private Integer qtdPedido;

    public Pedido() {
    }

    public Long getId() {
        return id;
    }

    public Integer getQtdPedido() {
        return qtdPedido;
    }

    public void setQtdPedido(Integer qtdPedido) {
        this.qtdPedido = qtdPedido;
    }

    public String getDscPedido() {
        return dscPedido;
    }

    public void setDscPedido(String dscPedido) {
        this.dscPedido = dscPedido;
    }
}
