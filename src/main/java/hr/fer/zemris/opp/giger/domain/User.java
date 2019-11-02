package hr.fer.zemris.opp.giger.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Email
    @NotNull
    private String email;
    private String phoneNumber;
    @NotNull
    private String passwordHash;
    private String pictureUrl;

    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "conversation_user",
            joinColumns = {@JoinColumn(name = "fk_user")},
            inverseJoinColumns = {@JoinColumn(name = "fk_conversation")})
    private List<Conversation> conversations;
}
