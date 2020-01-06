package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Gig;
import hr.fer.zemris.opp.giger.service.GigService;
import hr.fer.zemris.opp.giger.web.rest.dto.BandInvitation;
import hr.fer.zemris.opp.giger.web.rest.dto.GigCreationDto;
import hr.fer.zemris.opp.giger.web.rest.dto.GigPreviewDto;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/gigs")
public class GigController {

	private GigService gigService;

	@PostMapping("/create-gig")
	@PreAuthorize("hasPermission('ORGANIZER')")
	public Gig createGig(@Valid @RequestBody GigCreationDto gigCreationDto) {
		return gigService.createGig(gigCreationDto);
	}

	@GetMapping("/{gigId}")
	public GigPreviewDto viewGig(@PathVariable Long gigId) {
		return gigService.viewGig(gigId);
	}

	@PostMapping("/invite")
	public GigPreviewDto inviteBandToGig(@RequestBody BandInvitation bandInvitation) {
		return gigService.inviteBand(bandInvitation);
	}

	@GetMapping("/my")
	public List<GigPreviewDto> viewMyGigs() {
		return gigService.listMyGigs();
	}

	@PostMapping("/edit/{gigId}")
	public GigPreviewDto editGig(@RequestBody GigCreationDto gigCreationDto, @PathVariable Long gigId) {
		return gigService.editGig(gigCreationDto, gigId);
	}
}
