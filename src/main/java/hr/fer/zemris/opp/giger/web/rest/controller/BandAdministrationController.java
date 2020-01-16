package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.exception.GigerValidationException;
import hr.fer.zemris.opp.giger.service.BandService;
import hr.fer.zemris.opp.giger.web.rest.dto.*;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/band-administration")
@AllArgsConstructor
@PreAuthorize("hasAuthority('MUSICIAN')")
public class BandAdministrationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BandAdministrationController.class);
	private BandService bandService;

	@PostMapping("/create")
	public void createBand(@Valid @RequestBody BandCreationDto bandCreationDto, BindingResult bindingResult) {
		LOGGER.info("CreateBand: " + bandCreationDto);
		handleValidation(bindingResult);
		bandService.createBand(bandCreationDto);
	}

	@PostMapping("/invite")
	public void inviteUser(@Valid @RequestBody MusicianBandDto musicianBandDto, BindingResult bindingResult) {
		LOGGER.info("InviteUser: " + musicianBandDto);
		handleValidation(bindingResult);
		bandService.inviteMusician(musicianBandDto);
	}

	@PostMapping("/invite-as-backup")
	public void inviteBackUpMusician(@Valid @RequestBody MusicianBandDto musicianBandDto, BindingResult bindingResult) {
		LOGGER.info("InviteBackUpMusician: " + musicianBandDto);
		handleValidation(bindingResult);
		bandService.inviteBackUpMusician(musicianBandDto);
	}

	@GetMapping("/join/{bandId}")
	public void joinBand(@PathVariable @Min(1) Long bandId) {
		LOGGER.info("JoinBand: " + bandId);
		bandService.joinBand(bandId);
	}

	@GetMapping("/leave/{bandId}")
	public void leaveBand(@PathVariable @Min(1) Long bandId) {
		LOGGER.info("LeaveBand: " + bandId);
		bandService.leaveBand(bandId);
	}

	@PostMapping("/kick")
	public void kickMusician(@Valid @RequestBody KickDto kickDto, BindingResult bindingResult) {
		LOGGER.info("KickMusician: " + kickDto);
		handleValidation(bindingResult);
		bandService.kickMusician(kickDto);
	}

	@PostMapping("/edit")
	public void editProfile(@Valid @RequestBody BandEditProfileDto bandEditProfileDto, BindingResult bindingResult) {
		LOGGER.info("EditProfile: " + bandEditProfileDto);
		handleValidation(bindingResult);
		bandService.editProfile(bandEditProfileDto);
	}

	@PostMapping("/change-leader")
	public void changeLeader(@Valid @RequestBody MusicianBandDto musicianBandDto, BindingResult bindingResult) {
		LOGGER.info("CreateBand: " + musicianBandDto);
		handleValidation(bindingResult);
		bandService.changeLeader(musicianBandDto);
	}

	@GetMapping("/invites/{bandId}")
	public List<MusicianInvitationsDto> listInvitations(@PathVariable @Min(1) Long bandId) {
		LOGGER.info("ListInvitations: " + bandId);
		return bandService.listInvitations(bandId, 0);
	}

	@GetMapping("/back-up-invites/{bandId}")
	public List<MusicianInvitationsDto> listBackUpInvitations(@PathVariable @Min(1) Long bandId) {
		LOGGER.info("ListBackUpInvitations: " + bandId);
		return bandService.listInvitations(bandId, 1);
	}

	private void handleValidation(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			LOGGER.warn("Validation Exception: " + bindingResult.getAllErrors());
			throw new GigerValidationException(bindingResult);
		}
	}
}
