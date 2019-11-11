package hr.fer.zemris.opp.giger.repository;

import hr.fer.zemris.opp.giger.domain.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
}
