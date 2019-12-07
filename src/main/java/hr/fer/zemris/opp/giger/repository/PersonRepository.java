package hr.fer.zemris.opp.giger.repository;

import hr.fer.zemris.opp.giger.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findAllByUsername(String username);

    List<Person> findAllByUsernameLike(String username);

    Optional<Person> findByUsername(String username);

}
