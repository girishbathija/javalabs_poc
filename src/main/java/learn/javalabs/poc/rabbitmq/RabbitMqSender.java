package learn.javalabs.poc.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMqSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void send(String exchange ,String routingKey, String requestJson){
		log.info("Publishing message to RabbitMq!");
		rabbitTemplate.convertAndSend(exchange,routingKey,requestJson);
	}
}
