package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Band;
import hr.fer.zemris.opp.giger.domain.Musician;
import hr.fer.zemris.opp.giger.domain.Person;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/band-administration")
public class BandAdministrationController {

    @PostMapping("/create")
    public void createBand(Band band) {
    }

    @PostMapping("/invite")
    public void inviteUser(Musician musician, Band band) {
    }

    @PostMapping("/join")
    public void joinBand(Band band) {
    }

    @PostMapping("/leave/{bandId}")
    public void leaveBand() {
    }

    @PostMapping("/kick")
    public void kickUser(Band band, Musician musician) {
    }

    @PostMapping("/edit")
    public void editProfile(Band band) {
    }

    @PostMapping("/invites/{bandId}")
    public List<Person> seeInvitations() {
        return null;
    }
}
