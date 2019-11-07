package hr.fer.zemris.opp.giger.domain;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Email
    @NotNull
    @Column(unique = true)
    private String email;
    private Boolean verified;
    @Column(unique = true)
    private String username;
    private String phoneNumber;
    @NotNull
    private String passwordHash;
    private String pictureUrl;

    @OneToOne(fetch = LAZY)
    @JsonIgnore
    private Musician musician;

    @OneToOne(fetch = LAZY)
    @JsonIgnore
    private Organizer organizer;

    @ManyToMany
    @JoinTable(name = "conversation_user",
            joinColumns = {@JoinColumn(name = "fk_user")},
            inverseJoinColumns = {@JoinColumn(name = "fk_conversation")})
    private List<Conversation> conversations;
}
