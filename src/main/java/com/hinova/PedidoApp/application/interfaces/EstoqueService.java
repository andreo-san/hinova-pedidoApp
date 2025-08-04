package com.hinova.PedidoApp.application.interfaces;
import com.hinova.PedidoApp.domain.Pedido;

public interface EstoqueService {
    void consultarEstoque(Pedido dto) throws InterruptedException;
}