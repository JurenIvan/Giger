package hr.fer.zemris.opp.giger.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String contentOfReviewForMusician;
    private String contentOfReviewForOrganizer;
    private Integer gradeMusician;
    private Integer gradeOrganizer;

    private LocalDateTime created;

    @ManyToOne(fetch = EAGER)
    private User author;
}
