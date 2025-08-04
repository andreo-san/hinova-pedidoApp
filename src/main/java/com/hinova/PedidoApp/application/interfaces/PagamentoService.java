package com.hinova.PedidoApp.application.interfaces;
import com.hinova.PedidoApp.domain.Pedido;

public interface PagamentoService {
    void confirmaPagamento(Pedido dto) throws InterruptedException;
}