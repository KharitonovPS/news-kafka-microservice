package org.kharitonov.consumer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "wikimedia_recent_change")
public class Wikimedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Lob
    @Column(name = "wikimedia_data", columnDefinition = "CLOB")
    private String wikimediaData;
}
