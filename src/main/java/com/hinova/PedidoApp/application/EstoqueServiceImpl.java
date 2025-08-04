package com.hinova.PedidoApp.application;

import com.hinova.PedidoApp.application.interfaces.EstoqueService;
import com.hinova.PedidoApp.dto.PublishMessageDto;
import com.hinova.PedidoApp.domain.Pedido;
import com.hinova.PedidoApp.mensageria.MessagePublisher;
import org.springframework.stereotype.Service;

@Service
public class EstoqueServiceImpl implements EstoqueService {

    private final MessagePublisher publisher;

    public EstoqueServiceImpl(MessagePublisher publisher) {
        this.publisher = publisher;
    }

    public void consultarEstoque(Pedido dto) throws InterruptedException  {
        System.out.println("Simulando consulta de estoque para o pedido: " + dto.getId());
        Thread.sleep(1000);

        PublishMessageDto messageDto = new PublishMessageDto();
        messageDto.setObjeto(dto);
        messageDto.setExchange("pedido.exchange");
        messageDto.setRoutingKey("estoque.reserved");

        publisher.publishMessage(messageDto);
    }

}
