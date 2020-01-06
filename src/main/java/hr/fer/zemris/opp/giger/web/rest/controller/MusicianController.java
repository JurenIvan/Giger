package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.service.MusicianService;
import hr.fer.zemris.opp.giger.web.rest.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musicians")
@AllArgsConstructor
public class MusicianController {

	private MusicianService musicianService;

	@PostMapping("/create")
	public void create(@RequestBody MusicianDto musiciandto) {
		musicianService.createMusician(musiciandto);
	}

	@PostMapping("/edit")
	public void editProfile(@RequestBody MusicianProfileDto musicianProfileDto) {
		musicianService.editProfile(musicianProfileDto);
	}

	@GetMapping("/show/basic/{musicianId}")
	public MusicianProfileDto showBasicProfile(@PathVariable Long musicianId) {
		return musicianService.showProfile(musicianId);
	}

	@GetMapping("/show/occasions/{musicianId}")
	public List<OccasionDto> listOccasionsForMusician(@PathVariable Long musicianId) {
		return musicianService.getOccasions(musicianId);
	}

	@GetMapping("/show/posts/{musicianId}")
	public List<PostDto> listPostsForMusician(@PathVariable Long musicianId) {
		return musicianService.getPosts(musicianId);
	}

	@GetMapping("/all")
	public List<MusicianPreviewPictureDto> listAllMusicians() {
		return musicianService.getAllMusicians();
	}

	@PostMapping("/find")
	public List<MusicianPreviewPictureDto> findMusician(@RequestBody MusicianFinderDto musicianFinderDto) {
		return musicianService.findMusician(musicianFinderDto);
	}
}
