package hr.fer.zemris.opp.giger.service;

import hr.fer.zemris.opp.giger.config.errorHandling.GigerException;
import hr.fer.zemris.opp.giger.config.security.UserDetailsServiceImpl;
import hr.fer.zemris.opp.giger.domain.Band;
import hr.fer.zemris.opp.giger.domain.Musician;
import hr.fer.zemris.opp.giger.domain.Occasion;
import hr.fer.zemris.opp.giger.repository.BandRepository;
import hr.fer.zemris.opp.giger.repository.InstrumentRepository;
import hr.fer.zemris.opp.giger.repository.MusicianRepository;
import hr.fer.zemris.opp.giger.repository.ReviewRepository;
import hr.fer.zemris.opp.giger.web.rest.dto.BandPreviewDto;
import hr.fer.zemris.opp.giger.web.rest.dto.MusicianDto;
import hr.fer.zemris.opp.giger.web.rest.dto.MusicianProfileDto;
import hr.fer.zemris.opp.giger.web.rest.dto.ReviewPreviewDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static hr.fer.zemris.opp.giger.config.errorHandling.ErrorCode.MUSICIAN_ALREADY_EXISTS;
import static hr.fer.zemris.opp.giger.config.errorHandling.ErrorCode.NO_SUCH_MUSICIAN;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class MusicianService {

    private MusicianRepository musicianRepository;
    private InstrumentRepository instrumentRepository;
    private UserDetailsServiceImpl userDetailsService;
    private BandRepository bandRepository;
    private ReviewRepository reviewRepository;

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
        List<Occasion> occasions = getOccasions(musicianId);
        List<BandPreviewDto> bands = bandRepository.findAllByMembersContaining(musician).stream().map(Band::toDto).collect(toList());



        return new MusicianProfileDto();
    }
}
