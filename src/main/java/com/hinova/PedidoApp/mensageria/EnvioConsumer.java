package com.hinova.PedidoApp.mensageria;

import com.hinova.PedidoApp.application.EnvioServiceImpl;
import com.hinova.PedidoApp.application.interfaces.EnvioService;
import com.hinova.PedidoApp.domain.Pedido;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EnvioConsumer {

    private final EnvioService envioService;

    public EnvioConsumer(EnvioService envioService) {
        this.envioService = envioService;
    }

    @RabbitListener(queues = "envio.queue")
    public void enviaPedido(Pedido pedido) throws InterruptedException {
        envioService.enviaPedido(pedido);
    }
}
