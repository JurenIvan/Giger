package hr.fer.zemris.opp.giger.service;

import hr.fer.zemris.opp.giger.domain.exception.GigerException;
import hr.fer.zemris.opp.giger.config.security.UserDetailsServiceImpl;
import hr.fer.zemris.opp.giger.domain.*;
import hr.fer.zemris.opp.giger.repository.BandRepository;
import hr.fer.zemris.opp.giger.repository.InstrumentRepository;
import hr.fer.zemris.opp.giger.repository.MusicianRepository;
import hr.fer.zemris.opp.giger.repository.PersonRepository;
import hr.fer.zemris.opp.giger.web.rest.dto.MusicianDto;
import hr.fer.zemris.opp.giger.web.rest.dto.MusicianProfileDto;
import hr.fer.zemris.opp.giger.web.rest.dto.PostPreviewDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static hr.fer.zemris.opp.giger.domain.exception.ErrorCode.*;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class MusicianService {

    private PersonRepository personRepository;
    private MusicianRepository musicianRepository;
    private InstrumentRepository instrumentRepository;
    private UserDetailsServiceImpl userDetailsService;
    private BandRepository bandRepository;

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

    public List<Occasion> getOccasions(Long musicianId) {
        Musician loggedInMusician = userDetailsService.getLoggedMusician();
        Musician musician = musicianRepository.findById(musicianId).orElseThrow(() -> new GigerException(NO_SUCH_MUSICIAN));

        if (loggedInMusician.equals(musician))
            return musician.getOccasions();

        List<Musician> leaders = bandRepository.findAllByMembersContaining(musician).stream().map(Band::getLeader).collect(toList());
        if (leaders.contains(loggedInMusician))
            return musician.getOccasionsWithoutDescriptions();

        return musician.getPublicOccasios();
    }

    public MusicianProfileDto showProfile(Long musicianId) {
        Musician musician = musicianRepository.findById(musicianId).orElseThrow(() -> new GigerException(NO_SUCH_MUSICIAN));
        Person person = personRepository.findById(musicianId).orElseThrow(() -> new GigerException(NO_SUCH_USER));

        return new MusicianProfileDto(person.getUsername(), musician.getInstruments(), person.getPictureUrl(), person.getPhoneNumber());
    }

    public List<PostPreviewDto> getPosts(Long musicianId) {
        Musician musician = musicianRepository.findById(musicianId).orElseThrow(() -> new GigerException(NO_SUCH_MUSICIAN));
        return musician.getPosts().stream().map(Post::toDto).collect(toList());
    }

    public void editProfile(MusicianProfileDto musicianProfileDto, Long musicianId) {
        Musician musician = musicianRepository.findById(musicianId).orElseThrow(() -> new GigerException(NO_SUCH_MUSICIAN)).update(musicianProfileDto);
        Person person = personRepository.findById(musicianId).orElseThrow(() -> new GigerException(NO_SUCH_USER)).updatePerson(musicianProfileDto);

        musicianRepository.save(musician);
        personRepository.save(person);
    }
}
