package com.hinova.PedidoApp.config;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());

        // retry automático para conexão inicial
        RetryTemplate retryTemplate = new RetryTemplate();
        template.setRetryTemplate(retryTemplate);

        return template;
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // === Exchanges ===

    @Bean
    public TopicExchange pedidoExchange() {
        return new TopicExchange("pedido.exchange");
    }

    @Bean
    public TopicExchange pagamentoExchange() {
        return new TopicExchange("pagamento.exchange");
    }

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange("deadletter.exchange");
    }

    // === Queues ===

    @Bean
    public Queue estoqueQueue() {
        return QueueBuilder.durable("estoque.queue").build();
    }

    @Bean
    public Queue pagamentoQueue() {
        return QueueBuilder.durable("pagamento.queue").build();
    }

    @Bean
    public Queue envioQueue() {
        return QueueBuilder.durable("envio.queue").build();
    }

    @Bean
    public Queue notificacaoQueue() {
        return QueueBuilder.durable("notificacao.queue").build();
    }

    @Bean
    public Queue pedidoDLQ() {
        return QueueBuilder.durable("dlq.pedido.queue").build();
    }

    @Bean
    public Queue pagamentoDLQ() {
        return QueueBuilder.durable("dlq.pagamento.queue").build();
    }

    // === Bindings ===
    @Bean
    public Binding bindInventory(Queue estoqueQueue, TopicExchange pedidoExchange) {
        return BindingBuilder.bind(estoqueQueue)
                .to(pedidoExchange)
                .with("pedido.created");
    }

    @Bean
    public Binding bindPayment(Queue pagamentoQueue, TopicExchange pedidoExchange) {
        return BindingBuilder.bind(pagamentoQueue)
                .to(pedidoExchange)
                .with("estoque.reserved");
    }

    @Bean
    public Binding bindShipping(Queue envioQueue, TopicExchange pagamentoExchange) {
        return BindingBuilder.bind(envioQueue)
                .to(pagamentoExchange)
                .with("pagamento.success");
    }

    @Bean
    public Binding bindNotificationOrderCreated(Queue notificacaoQueue, TopicExchange pedidoExchange) {
        return BindingBuilder.bind(notificacaoQueue)
                .to(pedidoExchange)
                .with("pedido.created");
    }

    @Bean
    public Binding bindNotificationPaymentSuccess(Queue notificacaoQueue, TopicExchange pagamentoExchange) {
        return BindingBuilder.bind(notificacaoQueue)
                .to(pagamentoExchange)
                .with("pagamento.success");
    }

    @Bean
    public Binding bindNotificationShippingStarted(Queue notificacaoQueue, TopicExchange pagamentoExchange) {
        return BindingBuilder.bind(notificacaoQueue)
                .to(pagamentoExchange)
                .with("envio.started");
    }

    // DLQs

    @Bean
    public Binding bindDLQOrder(Queue pedidoDLQ, DirectExchange deadLetterExchange) {
        return BindingBuilder.bind(pedidoDLQ)
                .to(deadLetterExchange)
                .with("pedido.failed");
    }

    @Bean
    public Binding bindDLQPayment(Queue pagamentoDLQ, DirectExchange deadLetterExchange) {
        return BindingBuilder.bind(pagamentoDLQ)
                .to(deadLetterExchange)
                .with("pagamento.failed");
    }
}
