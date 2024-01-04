package org.kharitonov.publisher.service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.kharitonov.publisher.util.WikimediaNewsHandler;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class WikimediaNewsProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaNewsProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        EventHandler eventHandler = new WikimediaNewsHandler(kafkaTemplate);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder source = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource event = source.build();
        event.start();
        TimeUnit.MINUTES.sleep(10);
    }

    @PostConstruct
    void init() throws InterruptedException {
        WikimediaNewsProducer wikimediaNewsProducer = new WikimediaNewsProducer(kafkaTemplate);
        wikimediaNewsProducer.sendMessage();
    }
}
