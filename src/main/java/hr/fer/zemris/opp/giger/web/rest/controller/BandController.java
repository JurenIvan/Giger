package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.service.BandService;
import hr.fer.zemris.opp.giger.web.rest.dto.BandDto;
import hr.fer.zemris.opp.giger.web.rest.dto.BandInvitation;
import hr.fer.zemris.opp.giger.web.rest.dto.FilterBandDto;
import hr.fer.zemris.opp.giger.web.rest.dto.GigPreviewDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/bands")
public class BandController {

	private BandService bandService;

	@GetMapping("/{bandId}")
	public BandDto getBand(@PathVariable @Min(1) Long bandId) {
		return bandService.getBand(bandId);
	}

	@GetMapping("/like/{name}")
	public List<BandDto> getBands(@PathVariable @Min(1) String name) {
		return bandService.listBands(name);
	}

	@PostMapping("/filter") //todo improve
	public List<BandDto> filterBandsByFilters(FilterBandDto filterBandDto) {
		return bandService.listAvailableBands(filterBandDto);
	}

	@GetMapping("/invitations/{bandId}")
	public List<GigPreviewDto> getInvitations(@PathVariable @Min(1) Long bandId) {
		return bandService.getInvitations(bandId);
	}

	@PostMapping("/invitations/accept")
	public GigPreviewDto acceptInvitation(@Valid  @RequestBody BandInvitation bandInvitation) {
		return bandService.acceptInvitation(bandInvitation);
	}

	@PostMapping("/invitations/cancel")
	public void cancelInvitation(@Valid @RequestBody BandInvitation bandInvitation) {
		bandService.cancelInvitation(bandInvitation);
	}

	@GetMapping("/my/member")
	public List<BandDto> myBandsAsMember() {
		return bandService.myBandsMember();
	}

	@GetMapping("/my/leader")
	public List<BandDto> myBandsAsLeader() {
		return bandService.myBandsLeaders();
	}

}
