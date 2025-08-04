# PedidoApp

Desafio técnico.

Este projeto implementa um módulo de pedidos com uso de mensageria, seguindo princípios de **POO**, **SOLID** e **Arquitetura Hexagonal**, utilizando **Java + Spring Boot + RabbitMQ**.

---

## 🧩 Funcionalidade

Simula o processamento de pedidos com etapas como:

1. **Recebimento de pedido** via HTTP
2. **Consulta de estoque**
3. **Processamento de pagamento**
4. **Envio do pedido**

Cada etapa envia e consome mensagens via **RabbitMQ**, simulando um fluxo assíncrono entre os serviços.

---

## 🛠️ Tecnologias

- Java 17
- Spring Boot
- Spring AMQP
- RabbitMQ
- Maven

---
