package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Instrument;
import hr.fer.zemris.opp.giger.domain.exception.GigerValidationException;
import hr.fer.zemris.opp.giger.service.IntrumentService;
import hr.fer.zemris.opp.giger.web.rest.dto.InstrumentDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@AllArgsConstructor
@RestController
@RequestMapping("/instruments")
public class InstrumentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InstrumentController.class);
	private IntrumentService intrumentService;

	@GetMapping("/{instrumentId}")
	public Instrument getInstrument(@PathVariable Long instrumentId) {
		LOGGER.info("getInstrument: " + instrumentId);
		return intrumentService.getInstrument(instrumentId);
	}

	@PostMapping("/create")
	public Instrument createInstument(@Valid @RequestBody InstrumentDto instrumentDto, BindingResult bindingResult) {
		LOGGER.info("createInstument: " + instrumentDto);
		handleValidation(bindingResult);
		return intrumentService.saveInstrument(instrumentDto);
	}

	private void handleValidation(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			LOGGER.warn("Validation Exception: " + bindingResult.getAllErrors());
			throw new GigerValidationException(bindingResult);
		}
	}
}
