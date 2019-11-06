package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Musician;
import hr.fer.zemris.opp.giger.domain.Occasion;
import hr.fer.zemris.opp.giger.web.rest.dto.MusicianProfileDto;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class MusicianController  {

    public List<Occasion> listOccasionsForMusician(Musician musician){
        return null;
    }

    public void editProfile(){

    }

   public MusicianProfileDto showProfile(Musician musician){
        return null;
   }



}
