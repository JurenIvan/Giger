package hr.fer.zemris.opp.giger.service;

import hr.fer.zemris.opp.giger.config.errorHandling.GigerException;
import hr.fer.zemris.opp.giger.config.security.UserDetailsServiceImpl;
import hr.fer.zemris.opp.giger.domain.Musician;
import hr.fer.zemris.opp.giger.repository.InstrumentRepository;
import hr.fer.zemris.opp.giger.repository.MusicianRepository;
import hr.fer.zemris.opp.giger.web.rest.dto.MusicianDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static hr.fer.zemris.opp.giger.config.errorHandling.ErrorCode.MUSICIAN_ALREADY_EXISTS;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class MusicianService {

    private MusicianRepository musicianRepository;
    private InstrumentRepository instrumentRepository;
    private UserDetailsServiceImpl userDetailsService;

    public void createMusician(MusicianDto musicianDto) {
        if (userDetailsService.isLoggedUserMusician()) {
            throw new GigerException(MUSICIAN_ALREADY_EXISTS);
        }

        Musician musician = new Musician();
        musician.setId(userDetailsService.getLoggedInUserId());
        musician.setBio(musicianDto.getBio());
        musician.setInstruments(musicianDto.getInstrumentIdList().stream().map(e -> instrumentRepository.findById(e).get()).collect(toList()));

        musicianRepository.save(musician);
    }

    public void deleteMusician(Long musicianId) {
        musicianRepository.deleteById(musicianId);
    }
}
