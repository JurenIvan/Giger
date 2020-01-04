package hr.fer.zemris.opp.giger.repository;

import hr.fer.zemris.opp.giger.domain.Occasion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccasionRepository extends JpaRepository<Occasion, Long> {
}
