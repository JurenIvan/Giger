package hr.fer.zemris.opp.giger.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Message {

    @Id
    private Long id;
    private String content;
    private LocalDateTime sentTime;

    @ManyToOne
    @JoinColumn(name = "fk_sender")
    private User sender;

    @ManyToMany
    @JoinTable(name = "message_seen",
            joinColumns = {@JoinColumn(name = "fk_message")},
            inverseJoinColumns = {@JoinColumn(name = "fk_user")})
    private List<User> seenList;

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSentTime() {
        return sentTime;
    }

    public void setSentTime(LocalDateTime sentTime) {
        this.sentTime = sentTime;
    }

    public List<User> getSeenList() {
        return seenList;
    }

    public void setSeenList(List<User> seenList) {
        this.seenList = seenList;
    }
}
