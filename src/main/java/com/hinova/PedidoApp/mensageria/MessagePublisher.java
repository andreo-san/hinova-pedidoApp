package com.hinova.PedidoApp.mensageria;

import com.hinova.PedidoApp.dto.PublishMessageDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessagePublisher {

    private final RabbitTemplate rabbitTemplate;

    public MessagePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishMessage(PublishMessageDto dto) {
        rabbitTemplate.convertAndSend(dto.getExchange(), dto.getRoutingKey(), dto.getObjeto());
    }
}
