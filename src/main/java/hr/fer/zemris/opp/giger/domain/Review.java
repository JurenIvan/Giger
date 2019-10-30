package hr.fer.zemris.opp.giger.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.*;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String contentM;
    private String contentO;
    private Integer gradeM;
    private Integer gradeO;
    private LocalDateTime created;

    @ManyToOne(fetch = EAGER)
    private User author;
}
