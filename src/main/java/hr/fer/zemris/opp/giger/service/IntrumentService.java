package hr.fer.zemris.opp.giger.service;

import hr.fer.zemris.opp.giger.domain.Instrument;
import hr.fer.zemris.opp.giger.repository.InstrumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IntrumentService {
	private InstrumentRepository instrumentRepository;

	public List<Instrument> getListOfIntruments(List<Long> instrumentIdList) {
		return instrumentRepository.findAllByIdIn(instrumentIdList);
	}
}
