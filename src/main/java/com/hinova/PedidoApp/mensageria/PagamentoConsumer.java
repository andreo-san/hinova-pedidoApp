package com.hinova.PedidoApp.mensageria;

import com.hinova.PedidoApp.application.PagamentoServiceImpl;
import com.hinova.PedidoApp.application.interfaces.PagamentoService;
import com.hinova.PedidoApp.domain.Pedido;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PagamentoConsumer {

    private final PagamentoService pagamentoService;

    public PagamentoConsumer(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @RabbitListener(queues = "pagamento.queue")
    public void confirmaPagamento(Pedido pedido) throws InterruptedException {
        pagamentoService.confirmaPagamento(pedido);
    }
}
