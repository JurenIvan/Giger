package hr.fer.zemris.opp.giger.repository;

import hr.fer.zemris.opp.giger.domain.Musician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicianRepository extends JpaRepository<Musician, Long> {
}
