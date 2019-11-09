package hr.fer.zemris.opp.giger.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotNull
    private String content;
    @NotNull
    private LocalDateTime sentTime;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "fk_sender")
    @NotNull
    private Person sender;
}
