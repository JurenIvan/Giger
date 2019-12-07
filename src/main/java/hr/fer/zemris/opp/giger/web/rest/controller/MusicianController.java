package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Occasion;
import hr.fer.zemris.opp.giger.service.MusicianService;
import hr.fer.zemris.opp.giger.web.rest.dto.MusicianDto;
import hr.fer.zemris.opp.giger.web.rest.dto.MusicianProfileDto;
import hr.fer.zemris.opp.giger.web.rest.dto.PostPreviewDto;
import hr.fer.zemris.opp.giger.web.rest.dto.ReviewPreviewDto;
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

    @PostMapping("/edit/{musicianId}")
    public void editProfile(@PathVariable Long musicianId, @RequestBody MusicianProfileDto musicianProfileDto) {
        musicianService.editProfile(musicianProfileDto,musicianId);
    }

    @GetMapping("/show/basic/{musicianId}")
    public MusicianProfileDto showBasicProfile(@PathVariable Long musicianId) {
        return musicianService.showProfile(musicianId);
    }

    @GetMapping("/show/occasions/{musicianId}")
    public List<Occasion> listOccasionsForMusician(@PathVariable Long musicianId) {
        return musicianService.getOccasions(musicianId);
    }

    @GetMapping("/show/posts/{musicianId}")
    public List<PostPreviewDto> listPostsForMusician(@PathVariable Long musicianId) {
        return musicianService.getPosts(musicianId);
    }
}
