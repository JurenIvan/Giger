package hr.fer.zemris.opp.giger.service;

import hr.fer.zemris.opp.giger.config.security.UserDetailsServiceImpl;
import hr.fer.zemris.opp.giger.domain.Musician;
import hr.fer.zemris.opp.giger.domain.User;
import hr.fer.zemris.opp.giger.repository.InstrumentRepository;
import hr.fer.zemris.opp.giger.repository.MusicianRepository;
import hr.fer.zemris.opp.giger.repository.UserRepository;
import hr.fer.zemris.opp.giger.web.rest.dto.MusicianDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class MusicianService {

    private MusicianRepository musicianRepository;
    private UserRepository userRepository;
    private InstrumentRepository instrumentRepository;
    private UserDetailsServiceImpl userDetailsService;

    public void createMusician(MusicianDto musicianDto) {
        User user = userDetailsService.getLoggedUser();

        if (user.getMusician() != null) {
            return;
        }

        Musician musician = new Musician();
        musician.setId(user.getId());
        musician.setBio(musicianDto.getBio());
        musician.setInstruments(musicianDto.getInstrumentIdList().stream().map(e -> instrumentRepository.findById(e).get()).collect(toList()));

        musicianRepository.save(musician);
        user.setMusician(musician);
        userRepository.save(user);
    }
}
