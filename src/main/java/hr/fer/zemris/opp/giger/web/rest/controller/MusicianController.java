package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Musician;
import hr.fer.zemris.opp.giger.domain.Occasion;
import hr.fer.zemris.opp.giger.web.rest.dto.MusicianProfileDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/musicians")
public class MusicianController {

    public List<Occasion> listOccasionsForMusician(Musician musician) {
        return null;
    }

    @PostMapping("/edit")
    public void editProfile() {
    }

    @GetMapping("/show/{musicianId}")
    public MusicianProfileDto showProfile(Musician musician) {
        return null;
    }


}
