package org.kharitonov.publisher.util;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WikimediaNewsHandler implements EventHandler {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaNewsHandler(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        String event = messageEvent.getData();
        log.info("Event data -> {}", event);

        try {
            kafkaTemplate.send("wikimedia_recent_change", event);
            log.info("Message sent to Kafka topic");
        } catch (Exception e) {
            log.error("Error sending message to Kafka", e);
        }
    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {
    }
}
