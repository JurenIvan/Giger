package hr.fer.zemris.opp.giger.repository;

import hr.fer.zemris.opp.giger.domain.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Long> {

	Optional<Organizer> findAllByManagerName(String managerName);
}
