package com.gremlin.kafkaalfidemo;

import com.gremlin.GremlinService;
import com.gremlin.TrafficCoordinates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "users";

    private final GremlinService gremlinService;
    private TrafficCoordinates trafficCoordinates;

    @Autowired
    public Producer(GremlinService gremlinService) {
        this.gremlinService = gremlinService;
        this.trafficCoordinates = new TrafficCoordinates.Builder()
                .withType("KafkaALFIDemo")
                .withField("service", "producer")
                .build();
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String client, String ip) {
        gremlinService.applyImpact(trafficCoordinates);
        User user = new User(client, ip);

        logger.info(String.format("Logging User -> %s", user.toString()));
        this.kafkaTemplate.send(TOPIC, user.toString());
    }
}
