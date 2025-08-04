package com.hinova.PedidoApp;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PedidoAppApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(PedidoAppApplication.class, args);

		// FORÇA A EXECUÇÃO DO AMQP ADMIN
		AmqpAdmin admin = context.getBean(AmqpAdmin.class);
		admin.initialize();
	}

}
