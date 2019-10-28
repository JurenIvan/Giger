package hr.fer.zemris.opp.giger.domain;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Entity
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(fetch = LAZY) //todo think about it
    @JoinTable(name = "conversation_user",
            joinColumns = {@JoinColumn(name = "fk_conversation")},
            inverseJoinColumns = {@JoinColumn(name = "fk_user")})
    private List<User> participants;

    @OneToMany(fetch = EAGER, cascade = ALL)
    @JoinColumn(name = "fk_conversation")
    private List<Message> messages;

    public Conversation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
