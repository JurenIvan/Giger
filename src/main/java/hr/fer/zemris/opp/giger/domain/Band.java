package hr.fer.zemris.opp.giger.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Band {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @OneToMany
    private List<Musician> members;

    public Band() {
    }

    public List<Musician> getMembers() {
        return members;
    }

    public void setMembers(List<Musician> members) {
        this.members = members;
    }
}
