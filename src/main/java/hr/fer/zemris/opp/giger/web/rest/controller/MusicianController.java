package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Occasion;
import hr.fer.zemris.opp.giger.service.MusicianService;
import hr.fer.zemris.opp.giger.web.rest.dto.MusicianDto;
import hr.fer.zemris.opp.giger.web.rest.dto.MusicianProfileDto;
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

    @GetMapping("/get-occasions/{musicianId}")
    public List<Occasion> listOccasionsForMusician(@PathVariable Long musicianId) {
        return musicianService.getOccasions(musicianId);
    }

    @PostMapping("/edit/{musicianId}")
    public void editProfile(@RequestPart("musicianId") Long musicianId, MusicianProfileDto musicianProfileDto) {
    }

    @GetMapping("/show/{musicianId}")
    public MusicianProfileDto showProfile(@PathVariable Long musicianId) {
        return musicianService.showProfile(musicianId);
    }
}
