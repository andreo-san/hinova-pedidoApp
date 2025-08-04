FROM openjdk:17-jdk-slim

WORKDIR /app

# Instala netcat e curl
RUN apt-get update && apt-get install -y netcat curl && rm -rf /var/lib/apt/lists/*

# Copia o JAR gerado pelo Maven
COPY target/PedidoApp-0.0.1-SNAPSHOT.jar app.jar

# Baixa o wait-for-it.sh diretamente do GitHub
RUN curl -o /wait-for-it.sh https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh && \
    chmod +x /wait-for-it.sh

# Espera MySQL e RabbitMQ antes de iniciar a aplicação
CMD sh -c "/wait-for-it.sh db:3306 -- /wait-for-it.sh rabbitmq:5672 -- java -jar app.jar"
