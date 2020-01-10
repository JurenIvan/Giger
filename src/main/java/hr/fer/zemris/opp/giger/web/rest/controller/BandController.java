package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.exception.GigerValidationException;
import hr.fer.zemris.opp.giger.service.BandService;
import hr.fer.zemris.opp.giger.web.rest.dto.BandDto;
import hr.fer.zemris.opp.giger.web.rest.dto.BandInvitation;
import hr.fer.zemris.opp.giger.web.rest.dto.FilterBandDto;
import hr.fer.zemris.opp.giger.web.rest.dto.GigPreviewDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/bands")
public class BandController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BandController.class);
	private BandService bandService;

	@GetMapping("/{bandId}")
	public BandDto getBand(@PathVariable @Min(1) Long bandId, BindingResult bindingResult) {
		LOGGER.info("GetBand: " + bandId);
		handleValidation(bindingResult);
		return bandService.getBand(bandId);
	}

	@GetMapping("/like/{name}")
	public List<BandDto> getBands(@PathVariable @Min(1) String name, BindingResult bindingResult) {
		LOGGER.info("GetBands: " + name);
		handleValidation(bindingResult);
		return bandService.listBands(name);
	}

	@PostMapping("/filter") //todo improve
	public List<BandDto> filterBandsByFilters(FilterBandDto filterBandDto, BindingResult bindingResult) {
		LOGGER.info("FilterBandsByFilters: " + filterBandDto);
		handleValidation(bindingResult);
		return bandService.listAvailableBands(filterBandDto);
	}

	@GetMapping("/invitations/{bandId}")
	public List<GigPreviewDto> getInvitations(@PathVariable @Min(1) Long bandId, BindingResult bindingResult) {
		LOGGER.info("GetInvitations: " + bandId);
		handleValidation(bindingResult);
		return bandService.getInvitations(bandId);
	}

	@PostMapping("/invitations/accept")
	public GigPreviewDto acceptInvitation(@Valid @RequestBody BandInvitation bandInvitation, BindingResult bindingResult) {
		LOGGER.info("AcceptInvitation: " + bandInvitation);
		handleValidation(bindingResult);
		return bandService.acceptInvitation(bandInvitation);
	}

	@PostMapping("/invitations/cancel")
	public void cancelInvitation(@Valid @RequestBody BandInvitation bandInvitation, BindingResult bindingResult) {
		LOGGER.info("CancelInvitation: " + bandInvitation);
		handleValidation(bindingResult);
		bandService.cancelInvitation(bandInvitation);
	}

	@GetMapping("/my/member")
	public List<BandDto> myBandsAsMember() {
		LOGGER.info("MyBandsAsMember: ");
		return bandService.myBandsMember();
	}

	@GetMapping("/my/leader")
	public List<BandDto> myBandsAsLeader() {
		LOGGER.info("MyBandsAsLeader: ");
		return bandService.myBandsLeaders();
	}

	private void handleValidation(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			LOGGER.warn("Validation Exception: " + bindingResult.getAllErrors());
			throw new GigerValidationException(bindingResult);
		}
	}
}
