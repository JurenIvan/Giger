package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Gig;
import hr.fer.zemris.opp.giger.domain.exception.GigerValidationException;
import hr.fer.zemris.opp.giger.service.GigService;
import hr.fer.zemris.opp.giger.web.rest.dto.BandInvitation;
import hr.fer.zemris.opp.giger.web.rest.dto.GigCreationDto;
import hr.fer.zemris.opp.giger.web.rest.dto.GigPreviewDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/gigs")
public class GigController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GigController.class);
	private GigService gigService;

	@PostMapping("/create-gig")
	@PreAuthorize("hasPermission('ORGANIZER')")
	public Gig createGig(@Valid @RequestBody GigCreationDto gigCreationDto) {
		return gigService.createGig(gigCreationDto);
	}

	@GetMapping("/view/{gigId}")
	public GigPreviewDto viewGig(@PathVariable @Min(1) Long gigId) {
		return gigService.viewGig(gigId);
	}

	@PostMapping("/invite")
	public GigPreviewDto inviteBandToGig(@Valid @RequestBody BandInvitation bandInvitation) {
		return gigService.inviteBand(bandInvitation);
	}

	@GetMapping("/my")
	public List<GigPreviewDto> viewMyGigs() {
		return gigService.listMyGigs();
	}

	@PostMapping("/edit/{gigId}")
	public GigPreviewDto editGig(@Valid @RequestBody GigCreationDto gigCreationDto, @PathVariable Long gigId) {
		return gigService.editGig(gigCreationDto, gigId);
	}

	@GetMapping("get-public")
	public List<GigPreviewDto> listAllPublicGigs() {
		return gigService.listAllPublicGigs();
	}

	private void handleValidation(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			LOGGER.warn("Validation Exception: " + bindingResult.getAllErrors());
			throw new GigerValidationException(bindingResult);
		}
	}
}
