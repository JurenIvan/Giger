package hr.fer.zemris.opp.giger.service;

import hr.fer.zemris.opp.giger.config.security.UserDetailsServiceImpl;
import hr.fer.zemris.opp.giger.domain.*;
import hr.fer.zemris.opp.giger.domain.exception.GigerException;
import hr.fer.zemris.opp.giger.repository.*;
import hr.fer.zemris.opp.giger.web.rest.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static hr.fer.zemris.opp.giger.domain.exception.ErrorCode.*;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class BandService {

	private BandRepository bandRepository;
	private UserDetailsServiceImpl userDetailsService;
	private MusicianRepository musicianRepository;
	private PersonRepository personRepository;
	private GigRepository gigRepository;
	private OccasionRepository occasionRepository;

	public void createBand(BandCreationDto bandCreationDto) {
		if (bandRepository.findByName(bandCreationDto.getName()).isPresent()) {
			throw new GigerException(BAND_NAME_NOT_UNIQUE);
		}
		Band band = Band.createBand(bandCreationDto, userDetailsService.getLoggedMusician());

		bandRepository.save(band);
	}

	public void inviteMusician(MusicianBandDto musicianBandDto) {
		Band band = bandRepository.findById(musicianBandDto.getBandId()).orElseThrow(() -> new GigerException(NO_SUCH_BAND));
		Musician loggedMusician = userDetailsService.getLoggedMusician();
		if (!band.getLeader().getId().equals(loggedMusician.getId()))
			throw new GigerException(ONLY_LEADER_CAN_INVITE);
		band.inviteMember(musicianRepository.findById(musicianBandDto.getMusicianId()).orElseThrow(() -> new GigerException(NO_SUCH_MUSICIAN)));

		bandRepository.save(band);
	}

	public void inviteBackUpMusician(MusicianBandDto musicianBandDto) {
		Band band = bandRepository.findById(musicianBandDto.getBandId()).orElseThrow(() -> new GigerException(NO_SUCH_BAND));
		Musician loggedMusician = userDetailsService.getLoggedMusician();
		if (!band.getLeader().getId().equals(loggedMusician.getId()))
			throw new GigerException(ONLY_LEADER_CAN_INVITE);
		band.inviteBackupMember(musicianRepository.findById(musicianBandDto.getMusicianId()).orElseThrow(() -> new GigerException(NO_SUCH_MUSICIAN)));

		bandRepository.save(band);
	}

	public void joinBand(Long bandId) {
		Band band = bandRepository.findById(bandId).orElseThrow(() -> new GigerException(NO_SUCH_BAND));
		Musician loggedMusician = userDetailsService.getLoggedMusician();

		if (band.getInvited().contains(loggedMusician)) {
			band.addMember(loggedMusician);
		} else if (band.getInvitedBackUpMembers().contains(loggedMusician)) {
			band.addBackUpMember(loggedMusician);
		} else throw new GigerException(NOT_INVITED);

		bandRepository.save(band);
	}

	public void leaveBand(Long bandId) {
		Band band = bandRepository.findById(bandId).orElseThrow(() -> new GigerException(NO_SUCH_BAND));
		Musician loggedMusician = userDetailsService.getLoggedMusician();

		if (band.getMembers().contains(loggedMusician)) {
			band.removeMember(loggedMusician.getId());
		} else if (band.getBackUpMembers().contains(loggedMusician)) {
			band.removeBackUpMember(loggedMusician.getId());
		} else throw new GigerException(NOT_A_MEMBER_OF_BAND);

		bandRepository.save(band);
	}

	public void kickMusician(KickDto kickDto) {
		Band band = bandRepository.findById(kickDto.getBandId()).orElseThrow(() -> new GigerException(NO_SUCH_BAND));
		Musician loggedMusician = userDetailsService.getLoggedMusician();

		if (!band.getLeader().getId().equals(loggedMusician.getId()))
			throw new GigerException(ONLY_LEADER_CAN_KICK);
		band.removeMember(kickDto.getMusicianId());

		bandRepository.save(band);
	}

	public void editProfile(BandEditProfileDto bandEditProfileDto) {
		Band band = bandRepository.findById(bandEditProfileDto.getBandId()).orElseThrow(() -> new GigerException(NO_SUCH_BAND));
		Musician loggedMusician = userDetailsService.getLoggedMusician();

		if (!(band.getMembers().contains(loggedMusician) || band.getLeader().equals(loggedMusician)))
			throw new GigerException(ONLY_MEMBERS_CAN_EDIT_BAND);

		band.editProfile(bandEditProfileDto);
		bandRepository.save(band);
	}

	public List<MusicianInvitationsDto> listInvitations(Long bandId, int typeOfInvitation) {
		Band band = bandRepository.findById(bandId).orElseThrow(() -> new GigerException(NO_SUCH_BAND));
		Musician loggedMusician = userDetailsService.getLoggedMusician();

		if (!(band.getMembers().contains(loggedMusician) || band.getLeader().getId().equals(loggedMusician.getId())))
			throw new GigerException(ONLY_MEMBERS_CAN_SEE_INVITATIONS);

		List<MusicianInvitationsDto> result = new ArrayList<>();

		for (var musician : typeOfInvitation == 1 ? band.getInvitedBackUpMembers() : band.getInvited()) {
			Person person = personRepository.getOne(musician.getId());
			result.add(new MusicianInvitationsDto(musician.getId(), person.getUsername(), person.getPictureUrl(), musician.getBio(), musician.getInstruments()));
		}
		return result;
	}


	public void changeLeader(MusicianBandDto musicianBandDto) {
		Band band = bandRepository.findById(musicianBandDto.getBandId()).orElseThrow(() -> new GigerException(NO_SUCH_BAND));
		Musician loggedMusician = userDetailsService.getLoggedMusician();
		Musician newLeader = musicianRepository.findById(musicianBandDto.getMusicianId()).orElseThrow(() -> new GigerException(NO_SUCH_MUSICIAN));

		if (!band.getLeader().getId().equals(loggedMusician.getId()))
			throw new GigerException(ONLY_LEADER_CAN_CHANGE_LEADERSHIP);
		if (!band.getMembers().contains(newLeader))
			throw new GigerException(ONLY_MEMBER_CAN_BECOME_LEADER);
		band.setLeader(newLeader);

		bandRepository.save(band);
	}

	//todo improve
	public List<BandDto> listAvailableBands(FilterBandDto filterBandDto) {

		return bandRepository.findAll()
				.stream().map(Band::toDto).collect(toList());
	}

	public List<BandDto> listBands(String name) {
		return bandRepository.findAllByNameLike(name).stream().map(Band::toDto).collect(toList());
	}

	public List<GigPreviewDto> getInvitations(Long bandId) {
		Band band = bandRepository.findById(bandId).orElseThrow(() -> new GigerException(NO_SUCH_BAND));
		Musician loggedMusician = userDetailsService.getLoggedMusician();

		if (!(band.getMembers().contains(loggedMusician)) && !(band.getLeader().equals(loggedMusician)))
			throw new GigerException(NOT_A_MEMBER_OF_BAND);

		return band.getInvitationGigs().stream().map(Gig::toDto).collect(toList());
	}

	public GigPreviewDto acceptInvitation(BandInvitation bandInvitation) {
		Band band = bandRepository.findById(bandInvitation.getBandId()).orElseThrow(() -> new GigerException(NO_SUCH_BAND));
		Musician loggedMusician = userDetailsService.getLoggedMusician();
		Gig gig = gigRepository.findById(bandInvitation.getGigId()).orElseThrow(() -> new GigerException(NO_SUCH_GIG));

		if (!band.getLeader().getId().equals(loggedMusician.getId()))
			throw new GigerException(ONLY_LEADER_CAN_ACCEPT_GIG);

		if (band.getGigs().stream().anyMatch(e -> e.getId().equals(gig.getId())))
			throw new GigerException(BAND_ALREADY_ACCEPTED);

		if (band.getInvitationGigs().stream().noneMatch(e -> e.getId().equals(gig.getId())))
			throw new GigerException(BAND_NOT_INVITED_TO_GIG);

		gig.setFinalDealAchieved(true);
		gigRepository.save(gig);
		band.acceptGig(occasionRepository.save(Occasion.createOccasion(gig, false)), gig);
		bandRepository.save(band);

		return gig.toDto();
	}

	public BandDto getBand(Long bandId) {
		return bandRepository.findById(bandId).orElseThrow(() -> new GigerException(NO_SUCH_BAND)).toDto();
	}

	public void cancelInvitation(BandInvitation bandInvitation) {
		Band band = bandRepository.findById(bandInvitation.getBandId()).orElseThrow(() -> new GigerException(NO_SUCH_BAND));
		Musician loggedMusician = userDetailsService.getLoggedMusician();
		Gig gig = gigRepository.findById(bandInvitation.getGigId()).orElseThrow(() -> new GigerException(NO_SUCH_GIG));

		if (!band.getLeader().equals(loggedMusician))
			throw new GigerException(ONLY_LEADER_CAN_CANCEL_GIG);

		if (band.getGigs().stream().anyMatch(e -> e.equals(gig)))
			throw new GigerException(BAND_ALREADY_ACCEPTED);

		if (band.getInvitationGigs().stream().noneMatch(e -> e.equals(gig)))
			throw new GigerException(BAND_NOT_INVITED_TO_GIG);

		band.cancelGig(gig);
		bandRepository.save(band);
	}

	public List<BandDto> myBandsMember() {
		return bandRepository.findAllByMembersContaining(userDetailsService.getLoggedMusician()).stream().map(Band::toDto).collect(toList());
	}

	public List<BandDto> myBandsLeaders() {
		Musician musician = userDetailsService.getLoggedMusician();
		return bandRepository.findAllByMembersContaining(musician).stream().filter(e -> e.getLeader().getId().equals(musician.getId())).map(Band::toDto).collect(toList());
	}
}