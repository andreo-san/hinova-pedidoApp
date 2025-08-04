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

## ▶️ Como executar

Para rodar o projeto localmente com Docker Compose:

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/andreo-san/hinova-pedidoApp.git
   cd hinova-pedidoApp
   ```

2. **Gere o build da aplicação:**
   ```bash
   mvn clean package
   ```

3. **Suba os containers com Docker Compose:**
   ```bash
   docker-compose up --build
   ```

---

## 🌐 Endpoints e Serviços

- A **API REST** estará disponível em:  
  👉 [`http://localhost:8080`](http://localhost:8080)

- O **SWAGGER** estará disponível em:  
  👉 [`http://localhost:8080/swagger-ui/index.html#/`](http://localhost:8080/swagger-ui/index.html#)

- O **painel do RabbitMQ** estará disponível em:  
  👉 [`http://localhost:15672`](http://localhost:15672)

  **Credenciais padrão:**  
  - Usuário: `guest`  
  - Senha: `guest`

> Certifique-se de que as portas `8080` e `15672` estejam livres no seu sistema antes de executar o projeto.
---

Contato
[[Linkedin](https://www.linkedin.com/in/andré-santana-206a52209)]
