package org.kharitonov.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.kharitonov.consumer.entity.Wikimedia;
import org.kharitonov.consumer.repository.WikimediaDataRepo;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WikimediaNewsConsumer {

    public WikimediaNewsConsumer(WikimediaDataRepo wikimediaDataRepo) {
        this.wikimediaDataRepo = wikimediaDataRepo;
    }

    private final WikimediaDataRepo wikimediaDataRepo;

    @KafkaListener(
            topics = "wikimedia_recent_change",
            groupId = "myGroup"
    )
    public void consume(String eventMessage) {
        log.info("Message received -> {}", eventMessage);

        Wikimedia wikimediaData = new Wikimedia();
        wikimediaData.setWikimediaData(eventMessage);

        wikimediaDataRepo.save(wikimediaData);
    }
}
