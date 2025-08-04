package com.hinova.PedidoApp.application.interfaces;
import com.hinova.PedidoApp.domain.Pedido;

public interface PedidoService {
    Pedido createOrder(Pedido dto) throws InterruptedException;
}