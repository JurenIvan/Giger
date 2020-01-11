package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.exception.GigerValidationException;
import hr.fer.zemris.opp.giger.service.OrganizerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@RestController
@RequestMapping("/organizers")
public class OrganizerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizerController.class);
	private OrganizerService organizerService;

	@GetMapping("/create/{managerName}")
	public void create(@PathVariable @NotBlank String managerName) {
		LOGGER.info("Create: " + managerName);
		organizerService.createOrganizer(managerName);
	}

	@GetMapping("/edit/{managerName}")
	public void edit(@PathVariable @NotBlank String managerName) {
		LOGGER.info("Edit: " + managerName);
		organizerService.editOrganizer(managerName);
	}

	private void handleValidation(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			LOGGER.warn("Validation Exception: " + bindingResult.getAllErrors());
			throw new GigerValidationException(bindingResult);
		}
	}
}
