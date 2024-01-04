package org.kharitonov.consumer.repository;

import org.kharitonov.consumer.entity.Wikimedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WikimediaDataRepo extends JpaRepository <Wikimedia,Long> {
}
