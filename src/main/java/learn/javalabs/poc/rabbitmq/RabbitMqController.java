package learn.javalabs.poc.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/mq-publisher")
public class RabbitMqController {

	@Autowired
	private RabbitMqSender rabbitMqSender;

	@Autowired
	private RabbitMqConfig rabbitMqConfig;

	@ResponseBody
	@PostMapping(value = { "topic" })
	public ResponseEntity<String> publishToTopic(@RequestBody String requestJson, @RequestParam String routingKey) {
		return logAndPublish(rabbitMqConfig.topicExchange, routingKey, requestJson);
	}

	@ResponseBody
	@PostMapping(value = { "direct" })
	public ResponseEntity<String> publishToDirect(@RequestBody String requestJson, @RequestParam String routingKey) {
		return logAndPublish(rabbitMqConfig.directExchange, routingKey, requestJson);
	}

	@ResponseBody
	@PostMapping(value = { "fanout" })
	public ResponseEntity<String> publishToFanout(@RequestBody String requestJson) {
		return logAndPublish(rabbitMqConfig.fanoutExchange, "", requestJson);
	}

	private ResponseEntity<String> logAndPublish(String exchange, String routingKey, String message) {
		log.info("Request to publish on exchange - {}", exchange);
		rabbitMqSender.send(exchange, routingKey, message);
		return ResponseEntity.ok("ok");
	}
}