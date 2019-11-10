package hr.fer.zemris.opp.giger.service;

import hr.fer.zemris.opp.giger.config.errorHandling.ErrorCode;
import hr.fer.zemris.opp.giger.config.errorHandling.GigerException;
import hr.fer.zemris.opp.giger.config.security.UserDetailsServiceImpl;
import hr.fer.zemris.opp.giger.domain.Band;
import hr.fer.zemris.opp.giger.domain.Musician;
import hr.fer.zemris.opp.giger.domain.Occasion;
import hr.fer.zemris.opp.giger.domain.Person;
import hr.fer.zemris.opp.giger.repository.BandRepository;
import hr.fer.zemris.opp.giger.repository.MusicianRepository;
import hr.fer.zemris.opp.giger.repository.PersonRepository;
import hr.fer.zemris.opp.giger.web.rest.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static hr.fer.zemris.opp.giger.config.errorHandling.ErrorCode.BAND_NAME_NOT_UNIQUE;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class BandService {

    private BandRepository bandRepository;
    private UserDetailsServiceImpl userDetailsService;
    private MusicianRepository musicianRepository;
    private PersonRepository personRepository;

    public void createBand(BandCreationDto bandCreationDto) {
        if (bandRepository.findByName(bandCreationDto.getName()).isPresent()) {
            throw new GigerException(BAND_NAME_NOT_UNIQUE);
        }
        Band band = Band.createBand(bandCreationDto, userDetailsService.getLoggedMusician());

        bandRepository.save(band);
    }

    public void inviteMusician(MusicianBandDto musicianBandDto) {
        Band band = bandRepository.findById(musicianBandDto.getBandId()).orElseThrow(() -> new GigerException(ErrorCode.NO_SUCH_BAND));
        Musician loggedMusician = userDetailsService.getLoggedMusician();
        if (!band.getLeader().equals(loggedMusician))
            throw new GigerException(ErrorCode.ONLY_LEADER_CAN_INVITE);
        band.inviteMember(musicianRepository.findById(musicianBandDto.getMusicianId()).orElseThrow(() -> new GigerException(ErrorCode.NO_SUCH_MUSICIAN)));

        bandRepository.save(band);
    }

    public void inviteBackUpMusician(MusicianBandDto musicianBandDto) {
        Band band = bandRepository.findById(musicianBandDto.getBandId()).orElseThrow(() -> new GigerException(ErrorCode.NO_SUCH_BAND));
        Musician loggedMusician = userDetailsService.getLoggedMusician();
        if (!band.getLeader().equals(loggedMusician))
            throw new GigerException(ErrorCode.ONLY_LEADER_CAN_INVITE);
        band.inviteBackupMember(musicianRepository.findById(musicianBandDto.getMusicianId()).orElseThrow(() -> new GigerException(ErrorCode.NO_SUCH_MUSICIAN)));

        bandRepository.save(band);
    }

    public void joinBand(long bandId) {
        Band band = bandRepository.findById(bandId).orElseThrow(() -> new GigerException(ErrorCode.NO_SUCH_BAND));
        Musician loggedMusician = userDetailsService.getLoggedMusician();

        if (band.getInvited().contains(loggedMusician)) {
            band.addMember(loggedMusician);
        } else if (band.getInvitedBackUpMembers().contains(loggedMusician)) {
            band.addBackUpMember(loggedMusician);
        } else throw new GigerException(ErrorCode.NOT_INVITED);

        bandRepository.save(band);
    }

    public void leaveBand(long bandId) {
        Band band = bandRepository.findById(bandId).orElseThrow(() -> new GigerException(ErrorCode.NO_SUCH_BAND));
        Musician loggedMusician = userDetailsService.getLoggedMusician();

        if (band.getMembers().contains(loggedMusician)) {
            band.removeMember(loggedMusician);
        } else if (band.getBackUpMembers().contains(loggedMusician)) {
            band.removeBackUpMember(loggedMusician);
        } else throw new GigerException(ErrorCode.NOT_A_MEMBER_OF_BAND);

        bandRepository.save(band);
    }

    public void kickMusician(KickDto kickDto) {
        Band band = bandRepository.findById(kickDto.getBandId()).orElseThrow(() -> new GigerException(ErrorCode.NO_SUCH_BAND));
        Musician loggedMusician = userDetailsService.getLoggedMusician();
        if (!band.getLeader().equals(loggedMusician))
            throw new GigerException(ErrorCode.ONLY_LEADER_CAN_KICK);
        band.removeMember(musicianRepository.findById(kickDto.getMusicianId()).orElseThrow(() -> new GigerException(ErrorCode.NO_SUCH_MUSICIAN)));

        bandRepository.save(band);
    }

    public void kickBackUpMusician(long bandId, long musicianId) {
        Band band = bandRepository.findById(bandId).orElseThrow(() -> new GigerException(ErrorCode.NO_SUCH_BAND));
        Musician loggedMusician = userDetailsService.getLoggedMusician();
        if (!band.getLeader().equals(loggedMusician))
            throw new GigerException(ErrorCode.ONLY_LEADER_CAN_KICK);
        band.removeBackUpMember(musicianRepository.findById(musicianId).orElseThrow(() -> new GigerException(ErrorCode.NO_SUCH_MUSICIAN)));

        bandRepository.save(band);
    }

    public void editProfile(BandEditProfileDto bandEditProfileDto) {
        Band band = bandRepository.findById(bandEditProfileDto.getBandId()).orElseThrow(() -> new GigerException(ErrorCode.NO_SUCH_BAND));
        Musician loggedMusician = userDetailsService.getLoggedMusician();

        if (!band.getMembers().contains(loggedMusician))
            throw new GigerException(ErrorCode.ONLY_MEMBERS_CAN_EDIT_BAND);

        band.editProfile(bandEditProfileDto);
        bandRepository.save(band);
    }

    public List<MusicianInvitationsDto> listInvitations(long bandId) {
        Band band = bandRepository.findById(bandId).orElseThrow(() -> new GigerException(ErrorCode.NO_SUCH_BAND));
        Musician loggedMusician = userDetailsService.getLoggedMusician();

        if (!band.getMembers().contains(loggedMusician))
            throw new GigerException(ErrorCode.ONLY_MEMBERS_CAN_SEE_INVITATIONS);

        List<MusicianInvitationsDto> result = new ArrayList<>();
        for (var musician : band.getInvited()) {
            Person person = personRepository.getOne(musician.getId());
            result.add(new MusicianInvitationsDto(musician.getId(), person.getUsername(), person.getPictureUrl(), musician.getBio(), musician.getInstruments()));
        }
        return result;
    }


    public void changeLeader(MusicianBandDto musicianBandDto) {
        Band band = bandRepository.findById(musicianBandDto.getBandId()).orElseThrow(() -> new GigerException(ErrorCode.NO_SUCH_BAND));
        Musician loggedMusician = userDetailsService.getLoggedMusician();
        Musician newLeader = musicianRepository.findById(musicianBandDto.getMusicianId()).orElseThrow(() -> new GigerException(ErrorCode.NO_SUCH_MUSICIAN));

        if (!band.getLeader().equals(loggedMusician))
            throw new GigerException(ErrorCode.ONLY_LEADER_CAN_CHANGE_LEADERSHIP);
        if (!band.getMembers().contains(newLeader))
            throw new GigerException(ErrorCode.ONLY_MEMBER_CAN_BECOME_LEADER);
        band.setLeader(newLeader);

        bandRepository.save(band);
    }

    //needs some kind of smart algorithm to
    public List<BandPreviewDto> listAvailableBands(FilterBandDto filterBandDto) {
        Occasion o1 = new Occasion();
        Occasion o2 = new Occasion();
        o1.setLocalDate(filterBandDto.getSpecificDateFirst());
        o2.setLocalDate(filterBandDto.getSpecificDateSecond());


        return bandRepository.findAll()
                .stream().map(e -> e.toDto()).collect(toList());
    }

    public List<BandPreviewDto> listBands(String name) {
        return bandRepository.findAllByNameLike(name).stream().map(e -> e.toDto()).collect(toList());
    }
}