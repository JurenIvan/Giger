package hr.fer.zemris.opp.giger.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotNull
    private String content;
    @NotNull
    private LocalDateTime publishedOn;

    @OneToMany(fetch = EAGER, cascade = ALL)
    @JoinColumn(name = "fk_post")
    private List<Comment> comments;
}
