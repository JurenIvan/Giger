package hr.fer.zemris.opp.giger.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@Entity
public class SystemPerson {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Email
    @NotNull
    @Column(unique = true)
    private String email;
    private Boolean verified;
    @NotNull
    private String passwordHash;

}
