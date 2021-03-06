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

import static hr.fer.zemris.opp.giger.domain.enums.Role.MUSICIAN;
import static hr.fer.zemris.opp.giger.domain.exception.ErrorCode.*;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class MusicianService {

	private PersonRepository personRepository;
	private SystemPersonRepository systemPersonRepository;
	private MusicianRepository musicianRepository;
	private InstrumentRepository instrumentRepository;
	private UserDetailsServiceImpl userDetailsService;
	private BandRepository bandRepository;
	private IntrumentService intrumentService;

	public void createMusician(MusicianDto musicianDto) {

		if (userDetailsService.isLoggedUserMusician()) {
			throw new GigerException(MUSICIAN_ALREADY_EXISTS);
		}

		var loggedInUser = userDetailsService.getLoggedPerson();
		var loggedInUserId = loggedInUser.getId();
		var systemPerson = systemPersonRepository.findById(loggedInUserId).orElseThrow(() -> new GigerException(NO_SUCH_USER));
		systemPerson.addRole(MUSICIAN);

		Musician musician = new Musician();
		musician.setId(loggedInUserId);
		musician.setBio(musicianDto.getBio());
		musician.setInstruments(intrumentService.getListOfIntruments(musicianDto.getInstrumentIdList()));
		musician.setPublicCalendar(musicianDto.getPublicCalendar());

		loggedInUser.setPictureUrl("https://files.slack.com/files-pri/TP038T9EJ-FSRGY0QLQ/first.png");

		personRepository.save(loggedInUser);
		systemPersonRepository.save(systemPerson);
		musicianRepository.save(musician);
	}

	public List<OccasionDto> getOccasions(Long musicianId) {
		Musician loggedInMusician = userDetailsService.getLoggedMusician();
		Musician musician = musicianRepository.findById(musicianId).orElseThrow(() -> new GigerException(NO_SUCH_MUSICIAN));

		if (loggedInMusician.equals(musician))
			return musician.getOccasions().stream().map(Occasion::toDto).collect(toList());

		List<Musician> leaders = bandRepository.findAllByMembersContaining(musician).stream().map(Band::getLeader).collect(toList());
		if (leaders.contains(loggedInMusician))
			return musician.getOccasionsWithoutDescriptions().stream().map(Occasion::toDto).collect(toList());

		return musician.getPublicOccasios().stream().map(Occasion::toDto).collect(toList());
	}

	public ShowMusicianProfileDto showProfile(Long musicianId) {
		Musician musician = musicianRepository.findById(musicianId).orElseThrow(() -> new GigerException(NO_SUCH_MUSICIAN));
		Person person = personRepository.findById(musicianId).orElseThrow(() -> new GigerException(NO_SUCH_USER));

		return new ShowMusicianProfileDto(person.getUsername(), musician.getInstruments(), person.getPictureUrl(), person.getPhoneNumber());
	}

	public List<PostDto> getPosts(Long musicianId) {
		Musician musician = musicianRepository.findById(musicianId).orElseThrow(() -> new GigerException(NO_SUCH_MUSICIAN));
		MusicianProfileDto musicianProfileDto = showProfile(musician.getId()).toMusicianProfileDto();
		return musician.getPosts().stream().map(e -> e.toDto(musicianProfileDto, null)).collect(toList());
	}

	public void editProfile(MusicianProfileDto musicianProfileDto) {
		List<Instrument> instruments = null;
		if (musicianProfileDto.getInstrumentList() != null)
			instruments = intrumentService.getListOfIntruments(musicianProfileDto.getInstrumentList());
		Musician musician = userDetailsService.getLoggedMusician().update(instruments);
		Person person = userDetailsService.getLoggedPerson().updatePerson(musicianProfileDto);

		musicianRepository.save(musician);
		personRepository.save(person);
	}

	public List<MusicianPreviewPictureDto> getAllMusicians() {
		List<Musician> musicians = musicianRepository.findAll();
		return musicians.stream().map(e -> {
			Person person = personRepository.findById(e.getId()).orElseThrow(() -> new GigerException(NO_SUCH_USER));
			return e.createMusicianPreviewPicture(person.getUsername(), person.getPictureUrl());
		}).collect(toList());
	}

	public List<MusicianPreviewPictureDto> findMusician(MusicianFinderDto musicianFinderDto) {
		List<Person> people = personRepository.findByUsernameContaining(musicianFinderDto.getUserName());
		if (people.size() == 0) return List.of();
		return people.stream()
				.filter(e -> musicianRepository.findById(e.getId()).isPresent())
				.map(e -> musicianRepository.findById(e.getId()).orElseThrow(() -> new GigerException(NO_SUCH_MUSICIAN)).createMusicianPreviewPicture(e.getUsername(), e.getPictureUrl()))
				.collect(toList());
	}

	public List<MusicianInvitationDto> getMyInvitations() {
		Musician musician = userDetailsService.getLoggedMusician();
		List<Band> bandsInvitedAsMember = bandRepository.findAllByInvitedContaining(musician);
		List<Band> bandsInvitedAsBackup = bandRepository.findAllByInvitedBackUpMembersContaining(musician);

		List<MusicianInvitationDto> invitations = new ArrayList<>();

		bandsInvitedAsMember.forEach(e -> invitations.add(new MusicianInvitationDto(e.getId(), true, e.getName())));
		bandsInvitedAsBackup.forEach(e -> invitations.add(new MusicianInvitationDto(e.getId(), false, e.getName())));

		return invitations;
	}

	public void cancelInvitation(Long bandId) {
		Musician musician = userDetailsService.getLoggedMusician();

		Band band = bandRepository.findById(bandId).orElseThrow(() -> new GigerException(NO_SUCH_BAND));
		List<Band> bandsInvitedAsMember = bandRepository.findAllByInvitedContaining(musician);
		List<Band> bandsInvitedAsBackup = bandRepository.findAllByInvitedBackUpMembersContaining(musician);

		if (!bandsInvitedAsMember.contains(band) && !bandsInvitedAsBackup.contains(band))
			throw new GigerException(NOT_INVITED);
		else if (bandsInvitedAsMember.contains(band)) band.getInvited().remove(musician);
		else band.getInvitedBackUpMembers().remove(musician);

		bandRepository.save(band);
	}

	public List<Instrument> getMyInstruments() {
		Musician musician = userDetailsService.getLoggedMusician();

		return musician.getInstruments();
	}
}
