package hr.fer.zemris.opp.giger.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class Conversation {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String pictureUrl;

    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "conversation_user",
            joinColumns = {@JoinColumn(name = "fk_conversation")},
            inverseJoinColumns = {@JoinColumn(name = "fk_user")})
    private List<Person> participants;

    @OneToMany(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "fk_conversation")
    private List<Message> messages;
}
