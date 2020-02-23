package learn.javalabs.poc.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("consumer")
public class RabbitMqReceiver {

	@RabbitListener(queues = {"#{nonSelectiveQueue.name}","#{selectiveQueue.name}","#{circleQueue.name}","#{squareQueue.name}"})
	public void receiveFromTopicQueue(String in, @Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String key, @Header(AmqpHeaders.CONSUMER_QUEUE)String queue, @Header(AmqpHeaders.RECEIVED_EXCHANGE)String exchange){
		log.info("#MQ Msg received - Exchange - {}, Queue - {}, Key - {},  Message - {} ",exchange,queue,key,in);
	}

	@RabbitListener(queues = { "#{fanQueue1}", "#{fanQueue2}"})
	public void receiveFromTopicQueue(String in, @Header(AmqpHeaders.CONSUMER_QUEUE)String queue, @Header(AmqpHeaders.RECEIVED_EXCHANGE)String exchange){
		log.info("#MQ Msg received - Exchange - {}, Queue - {},  Message - {} ",exchange,queue,in);
	}
}