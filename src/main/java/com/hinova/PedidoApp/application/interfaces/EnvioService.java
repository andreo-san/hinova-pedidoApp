package com.hinova.PedidoApp.application.interfaces;
import com.hinova.PedidoApp.domain.Pedido;

public interface EnvioService {
    void enviaPedido(Pedido dto) throws InterruptedException;
}