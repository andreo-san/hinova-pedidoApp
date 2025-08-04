package com.hinova.PedidoApp.application;

import com.hinova.PedidoApp.application.interfaces.PagamentoService;
import com.hinova.PedidoApp.dto.PublishMessageDto;
import com.hinova.PedidoApp.domain.Pedido;
import com.hinova.PedidoApp.mensageria.MessagePublisher;
import org.springframework.stereotype.Service;

@Service
public class PagamentoServiceImpl implements PagamentoService {

    private final MessagePublisher publisher;

    public PagamentoServiceImpl(MessagePublisher publisher) {
        this.publisher = publisher;
    }

    public void confirmaPagamento(Pedido dto) throws InterruptedException {
        System.out.println("Simulando confirmação de pagamento para o pedido: " + dto.getId());
        Thread.sleep(1000);

        PublishMessageDto messageDto = new PublishMessageDto();
        messageDto.setObjeto(dto);
        messageDto.setExchange("pagamento.exchange");
        messageDto.setRoutingKey("pagamento.success");

        publisher.publishMessage(messageDto);
    }

}
