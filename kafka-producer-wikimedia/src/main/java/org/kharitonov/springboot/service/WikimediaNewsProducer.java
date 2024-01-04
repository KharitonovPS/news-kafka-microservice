package org.kharitonov.springboot.service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.kharitonov.springboot.util.WikimediaNewsHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class WikimediaNewsProducer {

    @PostConstruct
    void init() throws InterruptedException {
        WikimediaNewsProducer wikimediaNewsProducer = new WikimediaNewsProducer(kafkaTemplate);
        wikimediaNewsProducer.sendMessage();
    }

    @Value("${spring.kafka.topic_name}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaNewsProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        EventHandler eventHandler = new WikimediaNewsHandler(topic, kafkaTemplate);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder source = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource event = source.build();
        event.start();
        TimeUnit.MINUTES.sleep(10);
    }
}
