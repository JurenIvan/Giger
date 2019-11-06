package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Band;
import hr.fer.zemris.opp.giger.domain.Musician;
import hr.fer.zemris.opp.giger.domain.User;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/band-administration")
public class BandAdministrationController {

    public void createBand(Band band){
    }

    public void inviteUser(Musician musician){
    }

    public void joinBand(Band band) {
    }

    public void leaveBand(Band band){
    }

    public void kickUser(Band band,Musician musician){
    }

    public void editProfile(Band band){

    }

    public List<User> seeInvitations(Band band) {
        return null;
    }
}
