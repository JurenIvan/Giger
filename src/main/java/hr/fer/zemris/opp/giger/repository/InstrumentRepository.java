package hr.fer.zemris.opp.giger.repository;

import hr.fer.zemris.opp.giger.domain.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {

	List<Instrument> findAllByIdIn(List<Long> id);
}
