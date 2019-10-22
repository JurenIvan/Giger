package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.domain.Musician;
import hr.fer.zemris.opp.giger.repository.MusicianRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloWorldController {

    private MusicianRepository musicianRepository;

    public HelloWorldController(MusicianRepository musicianRepository) {
        this.musicianRepository = musicianRepository;
    }

    @RequestMapping("/hello-world")
    public String helloWorld() {
        return "Hello!";
    }

    @RequestMapping("/get-musicians")
    public List<Musician> getMusicians(){
        return musicianRepository.findAll();
    }

}
