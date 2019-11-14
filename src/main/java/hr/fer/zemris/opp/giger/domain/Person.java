package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.web.rest.dto.PersonPreviewDto;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
    @Length(max = 10000)
    private String pictureUrl;

    public PersonPreviewDto toDto() {
        return new PersonPreviewDto(id, username, pictureUrl);
    }
}