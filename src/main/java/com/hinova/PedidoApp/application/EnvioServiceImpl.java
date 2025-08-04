package com.hinova.PedidoApp.application;

import com.hinova.PedidoApp.application.interfaces.EnvioService;
import com.hinova.PedidoApp.dto.PublishMessageDto;
import com.hinova.PedidoApp.domain.Pedido;
import com.hinova.PedidoApp.mensageria.MessagePublisher;
import org.springframework.stereotype.Service;

@Service
public class EnvioServiceImpl implements EnvioService {

    private final MessagePublisher publisher;

    public EnvioServiceImpl(MessagePublisher publisher) {
        this.publisher = publisher;
    }

    public void enviaPedido(Pedido dto) throws InterruptedException {
        System.out.println("Simulando o envio do pedido: " + dto.getId());
        Thread.sleep(1000);

        PublishMessageDto messageDto = new PublishMessageDto();
        messageDto.setObjeto(dto);
        messageDto.setExchange("pagamento.exchange");
        messageDto.setRoutingKey("envio.started");

        publisher.publishMessage(messageDto);
    }

}
