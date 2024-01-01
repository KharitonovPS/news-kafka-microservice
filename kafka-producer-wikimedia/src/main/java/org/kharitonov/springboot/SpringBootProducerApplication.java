package org.kharitonov.springboot;

import org.kharitonov.springboot.producer.WikimediaNewsProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootProducerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProducerApplication.class);

    }

    private final WikimediaNewsProducer wikimediaNewsProducer;

    public SpringBootProducerApplication(WikimediaNewsProducer wikimediaNewsProducer) {
        this.wikimediaNewsProducer = wikimediaNewsProducer;
    }


    @Override
    public void run(String... args) throws Exception {
        wikimediaNewsProducer.sendMessage();
    }
}
