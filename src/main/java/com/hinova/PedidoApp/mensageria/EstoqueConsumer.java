package com.hinova.PedidoApp.mensageria;

import com.hinova.PedidoApp.application.EstoqueServiceImpl;
import com.hinova.PedidoApp.application.interfaces.EstoqueService;
import com.hinova.PedidoApp.domain.Pedido;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EstoqueConsumer {

    private final EstoqueService estoqueService;

    public EstoqueConsumer(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @RabbitListener(queues = "estoque.queue")
    public void receberPedido(Pedido pedido) throws InterruptedException  {
        estoqueService.consultarEstoque(pedido);
    }
}
