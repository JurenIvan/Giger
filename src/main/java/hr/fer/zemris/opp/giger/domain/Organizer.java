package hr.fer.zemris.opp.giger.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organizer {

    @Id
    private Long id;
    private String managerName;

    @OneToMany
    @JoinColumn(name = "organizer_id")
    @JsonIgnore
    private List<Gig> history;

    @OneToOne(fetch = LAZY)
    @JsonIgnore
    private User user;
}
