package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Instrument;
import hr.fer.zemris.opp.giger.domain.exception.GigerValidationException;
import hr.fer.zemris.opp.giger.service.MusicianService;
import hr.fer.zemris.opp.giger.web.rest.dto.*;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/musicians")
@AllArgsConstructor
public class MusicianController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MusicianController.class);
	private MusicianService musicianService;

	@PostMapping("/create")
	public void create(@Valid @RequestBody MusicianDto musiciandto, BindingResult bindingResult) {
		LOGGER.info("Create: " + musiciandto);
		handleValidation(bindingResult);
		musicianService.createMusician(musiciandto);
	}

	@PostMapping("/edit")
	public void editProfile(@Valid @RequestBody MusicianProfileDto musicianProfileDto, BindingResult bindingResult) {
		LOGGER.info("EditProfile: " + musicianProfileDto);
		handleValidation(bindingResult);
		musicianService.editProfile(musicianProfileDto);
	}

	@GetMapping("/show/basic/{musicianId}")
	public MusicianProfileDto showBasicProfile(@PathVariable @Min(1) Long musicianId) {
		LOGGER.info("ShowBasicProfile: " + musicianId);
		return musicianService.showProfile(musicianId);
	}

	@GetMapping("/show/occasions/{musicianId}")
	public List<OccasionDto> listOccasionsForMusician(@PathVariable @Min(1) Long musicianId) {
		LOGGER.info("ListOccasionsForMusician: " + musicianId);
		return musicianService.getOccasions(musicianId);
	}

	@GetMapping("/show/posts/{musicianId}")
	public List<PostDto> listPostsForMusician(@PathVariable @Min(1) Long musicianId) {
		LOGGER.info("ListPostsForMusician: " + musicianId);
		return musicianService.getPosts(musicianId);
	}

	@GetMapping("/all")
	public List<MusicianPreviewPictureDto> listAllMusicians() {
		LOGGER.info("ListAllMusicians");
		return musicianService.getAllMusicians();
	}

	@PostMapping("/find")
	public List<MusicianPreviewPictureDto> findMusician(@RequestBody MusicianFinderDto musicianFinderDto, BindingResult bindingResult) {
		LOGGER.info("CreateGig: " + musicianFinderDto);
		handleValidation(bindingResult);
		return musicianService.findMusician(musicianFinderDto);
	}

	@GetMapping("/invitations")
	public List<MusicianInvitationDto> getMyInvitations() {
		LOGGER.info("GetMyInvitations");
		return musicianService.getMyInvitations();
	}

	@GetMapping("/invitations/cancel/{bandId}")
	public void cancelInvitation(@PathVariable @Min(1) Long bandId) {
		LOGGER.info("CancelInvitation: " + bandId);
		musicianService.cancelInvitation(bandId);
	}

	@GetMapping("/my/instruments")
	public List<Instrument> listInstruments() {
		LOGGER.info("ListInstruments");
		return musicianService.getMyInstruments();
	}

	private void handleValidation(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			LOGGER.warn("Validation Exception: " + bindingResult.getAllErrors());
			throw new GigerValidationException(bindingResult);
		}
	}
}
