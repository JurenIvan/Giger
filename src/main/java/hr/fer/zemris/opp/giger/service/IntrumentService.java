package hr.fer.zemris.opp.giger.service;

import hr.fer.zemris.opp.giger.domain.Instrument;
import hr.fer.zemris.opp.giger.domain.exception.GigerException;
import hr.fer.zemris.opp.giger.repository.InstrumentRepository;
import hr.fer.zemris.opp.giger.web.rest.dto.InstrumentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static hr.fer.zemris.opp.giger.domain.exception.ErrorCode.NO_SUCH_INSTRUMENT;

@Service
@AllArgsConstructor
public class IntrumentService {
	private InstrumentRepository instrumentRepository;

	public List<Instrument> getListOfIntruments(List<Long> instrumentIdList) {
		return instrumentRepository.findAllByIdIn(instrumentIdList);
	}

	public Instrument getInstrument(Long instrumentId) {
		return instrumentRepository.findById(instrumentId).orElseThrow(() -> new GigerException(NO_SUCH_INSTRUMENT));
	}

	public Instrument saveInstrument(InstrumentDto instrumentDto) {
		return instrumentRepository.save(new Instrument(null, instrumentDto.getName(), instrumentDto.getType(), instrumentDto.getPictureUrl()));
	}
}
