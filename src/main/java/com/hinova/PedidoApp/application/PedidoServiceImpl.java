package com.hinova.PedidoApp.application;

import com.hinova.PedidoApp.application.interfaces.PedidoService;
import com.hinova.PedidoApp.dto.PublishMessageDto;
import com.hinova.PedidoApp.domain.Pedido;
import com.hinova.PedidoApp.exception.PedidoNotFoundException;
import com.hinova.PedidoApp.mensageria.MessagePublisher;
import com.hinova.PedidoApp.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final MessagePublisher publisher;
    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(MessagePublisher publisher, PedidoRepository pedidoRepository) {
        this.publisher = publisher;
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido createOrder(Pedido order) {
        PublishMessageDto messageDto = new PublishMessageDto();
        messageDto.setExchange("pedido.exchange");
        messageDto.setRoutingKey("pedido.created");
        messageDto.setObjeto(order);

        pedidoRepository.save(order);
        publisher.publishMessage(messageDto);

        return order;
    }

    public Pedido findById(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException("Pedido não encontrado id: " + id));
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedido updateOrder(Long id, Pedido updatedOrder) {
        Pedido existingOrder = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException("Pedido não encontrado id: " + id));

        existingOrder.setDscPedido(updatedOrder.getDscPedido());
        existingOrder.setQtdPedido(updatedOrder.getQtdPedido());

        return pedidoRepository.save(existingOrder);
    }

    public void deleteOrder(Long id) {
        Pedido existingOrder = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException("Pedido não encontrado id: " + id));
        pedidoRepository.delete(existingOrder);
    }
}
