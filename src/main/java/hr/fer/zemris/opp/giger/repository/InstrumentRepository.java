package hr.fer.zemris.opp.giger.repository;

import hr.fer.zemris.opp.giger.domain.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrumentRepository extends JpaRepository<Instrument, Long> {

	List<Instrument> findAllByIdIn(List<Long> id);
	List<Instrument> findAll();
}
