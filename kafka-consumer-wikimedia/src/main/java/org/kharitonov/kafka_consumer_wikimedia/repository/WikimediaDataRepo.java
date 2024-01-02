package org.kharitonov.kafka_consumer_wikimedia.repository;

import org.kharitonov.kafka_consumer_wikimedia.entity.Wikimedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WikimediaDataRepo extends JpaRepository <Wikimedia,Long> {
}
