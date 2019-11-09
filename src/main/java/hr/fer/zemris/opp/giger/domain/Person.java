package hr.fer.zemris.opp.giger.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "people")
public class Person {

    @Id
    private Long id;

    @Column(unique = true)
    private String username;
    private String phoneNumber;
    private String pictureUrl;
}
