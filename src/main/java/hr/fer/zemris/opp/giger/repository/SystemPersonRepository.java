package hr.fer.zemris.opp.giger.repository;

import hr.fer.zemris.opp.giger.domain.SystemPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemPersonRepository extends JpaRepository<SystemPerson, Long> {

	Optional<SystemPerson> findByEmail(String Email);
}
