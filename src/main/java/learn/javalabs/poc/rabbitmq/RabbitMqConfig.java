package learn.javalabs.poc.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:rabbitmq.properties")
public class RabbitMqConfig {

	@Value("${exchange.topic}")
	public String topicExchange;

	@Value("${topic.queue1}")
	public String nonSelectiveQueue;
	@Value("${topic.queue1.binding}")
	public String nonSelectiveQueueBinding;

	@Value("${topic.queue2}")
	public String selectiveQueue;
	@Value("${topic.queue2.binding}")
	public String selectiveQueueBinding;

	@Value("${exchange.direct}")
	public String directExchange;

	@Value("${direct.queue1}")
	public String circleQueue;
	@Value("${direct.queue1.binding}")
	public String circleQueueBinding;

	@Value("${direct.queue2}")
	public String squareQueue;
	@Value("${direct.queue2.binding}")
	public String squareQueueBinding;

	@Value("${exchange.fanout}")
	public String fanoutExchange;

	@Value("${fanout.queue1}")
	public String fanQueue1;
	@Value("${fanout.queue2}")
	public String fanQueue2;


	@Bean
	public TopicExchange topic(){
		return new TopicExchange(topicExchange,false,true);
	}

	@Bean
	public Queue nonSelectiveQueue(){
		return new Queue(nonSelectiveQueue,false,false, true);
	}

	@Bean
	public Binding nonSelectiveBinding(TopicExchange topic, Queue nonSelectiveQueue) {
		return BindingBuilder.bind(nonSelectiveQueue).to(topic).with(nonSelectiveQueueBinding);
	}

	@Bean
	public Queue selectiveQueue(){
		return new Queue(selectiveQueue,false,false, true);
	}

	@Bean
	public Binding selectiveBinding(TopicExchange topic, Queue selectiveQueue) {
		return BindingBuilder.bind(selectiveQueue).to(topic).with(selectiveQueueBinding);
	}

	@Bean
	public DirectExchange direct(){
		return new DirectExchange(directExchange,false,true);
	}

	@Bean
	public Queue circleQueue(){
		return new Queue(circleQueue,false,false, true);
	}

	@Bean
	public Binding circleBinding(DirectExchange direct, Queue circleQueue) {
		return BindingBuilder.bind(circleQueue).to(direct).with(circleQueueBinding);
	}

	@Bean
	public Queue squareQueue(){
		return new Queue(squareQueue,false,false, true);
	}

	@Bean
	public Binding squareBinding(DirectExchange direct, Queue squareQueue) {
		return BindingBuilder.bind(squareQueue).to(direct).with(squareQueueBinding);
	}

	@Bean
	public FanoutExchange fanout(){
		return new FanoutExchange(fanoutExchange,false,true);
	}

	@Bean
	public Queue fanQueue1(){
		return new Queue(fanQueue1,false,false, true);
	}
	@Bean
	public Queue fanQueue2(){
		return new Queue(fanQueue2,false,false, true);
	}

	@Bean
	public Binding fanQ1Binding(FanoutExchange fanout, Queue fanQueue1) {
		return BindingBuilder.bind(fanQueue1).to(fanout);
	}
	@Bean
	public Binding fanQ2Binding(FanoutExchange fanout, Queue fanQueue2) {
		return BindingBuilder.bind(fanQueue2).to(fanout);
	}

}
