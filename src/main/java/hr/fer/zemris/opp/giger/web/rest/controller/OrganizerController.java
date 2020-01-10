package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.service.OrganizerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@AllArgsConstructor
@RestController
@RequestMapping("/organizers")
public class OrganizerController {

	private OrganizerService organizerService;

	@GetMapping("/create/{managerName}")
	public void create(@PathVariable @Min(1) String managerName) {
		organizerService.createOrganizer(managerName);
	}

	@GetMapping("/edit/{managerName}")
	public void edit(@PathVariable @Min(1) String managerName) {
		organizerService.editOrganizer(managerName);
	}
}
