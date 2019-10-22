package hr.fer.zemris.opp.giger.repository;

import hr.fer.zemris.opp.giger.domain.Musician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicianRepository extends JpaRepository<Musician, Long> {

    List<Musician> findByInstrument(String instrument);
}
