package com.gremlin.kafkaalfidemo;

import com.gremlin.GremlinService;
import com.gremlin.TrafficCoordinates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    private final GremlinService gremlinService;
    private TrafficCoordinates trafficCoordinates;

    @Autowired
    public Consumer(GremlinService gremlinService) {
        this.gremlinService = gremlinService;
        this.trafficCoordinates = new TrafficCoordinates.Builder()
                .withType("KafkaALFIDemo")
                .withField("service", "consumer")
                .build();
    }

    @KafkaListener(topics = "users", groupId = "group_id")
    public void consume(String message) throws IOException {
        gremlinService.applyImpact(trafficCoordinates);
        logger.info(String.format("Consumed message -> %s", message));
    }
}
