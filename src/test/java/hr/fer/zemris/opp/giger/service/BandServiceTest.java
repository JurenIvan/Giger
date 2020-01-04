package hr.fer.zemris.opp.giger.service;

import hr.fer.zemris.opp.giger.config.security.UserDetailsServiceImpl;
import hr.fer.zemris.opp.giger.domain.Band;
import hr.fer.zemris.opp.giger.domain.Musician;
import hr.fer.zemris.opp.giger.domain.exception.GigerException;
import hr.fer.zemris.opp.giger.repository.*;
import hr.fer.zemris.opp.giger.web.rest.dto.MusicianBandDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.util.Lists.newArrayList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class BandServiceTest {

	@Mock
	private BandRepository bandRepository;
	@Mock
	private UserDetailsServiceImpl userDetailsService;
	@Mock
	private MusicianRepository musicianRepository;
	@Mock
	private PersonRepository personRepository;
	@Mock
	private GigRepository gigRepository;
	@Mock
	private OccasionRepository occasionRepository;
	@InjectMocks
	BandService bandService;

	@Before
	public void setUp() {
	}

	@Test
	public void createBand() {
	}

	@Test(expected = GigerException.class)
	public void inviteMusician_noSuchBand() {
		when(bandRepository.findById(1L)).thenReturn(empty());

		MusicianBandDto musicianBandDto = new MusicianBandDto();
		musicianBandDto.setMusicianId(1);
		musicianBandDto.setBandId(1);

		bandService.inviteMusician(musicianBandDto);
	}

	@Test(expected = GigerException.class)
	public void inviteMusician_noSuchMusician() {
		Musician musician_loggedIn = mock(Musician.class);

		Band band = new Band();
		band.setLeader(musician_loggedIn);

		when(bandRepository.findById(1L)).thenReturn(of(band));
		when(musician_loggedIn.getId()).thenReturn(1L);
		when(userDetailsService.getLoggedMusician()).thenReturn(musician_loggedIn);
		when(musicianRepository.findById(2L)).thenReturn(empty());

		MusicianBandDto musicianBandDto = new MusicianBandDto();
		musicianBandDto.setMusicianId(2);
		musicianBandDto.setBandId(1);

		bandService.inviteMusician(musicianBandDto);
	}

	@Test
	public void inviteMusician_assertCreated() {
		Musician musician_invitee = mock(Musician.class);
		Musician musician_loggedIn = mock(Musician.class);

		Band band = new Band();
		band.setLeader(musician_loggedIn);
		band.setInvited(new ArrayList<>());

		MusicianBandDto musicianBandDto = new MusicianBandDto();
		musicianBandDto.setMusicianId(2);
		musicianBandDto.setBandId(1);

		when(bandRepository.findById(1L)).thenReturn(of(band));
		when(musician_loggedIn.getId()).thenReturn(1L);
		when(userDetailsService.getLoggedMusician()).thenReturn(musician_loggedIn);
		when(musicianRepository.findById(2L)).thenReturn(of(musician_invitee));

		bandService.inviteMusician(musicianBandDto);

		verify(bandRepository).save(band);
		assertTrue(band.getInvited().contains(musician_invitee));
	}


	@Test(expected = GigerException.class)
	public void inviteBackUpMusician_noSuchBand() {
		when(bandRepository.findById(1L)).thenReturn(empty());

		MusicianBandDto musicianBandDto = new MusicianBandDto();
		musicianBandDto.setMusicianId(1);
		musicianBandDto.setBandId(1);

		bandService.inviteMusician(musicianBandDto);
	}

	@Test(expected = GigerException.class)
	public void inviteBackUpMusician_noSuchMusician() {
		Musician musician_loggedIn = mock(Musician.class);

		Band band = new Band();
		band.setLeader(musician_loggedIn);

		when(bandRepository.findById(1L)).thenReturn(of(band));
		when(musician_loggedIn.getId()).thenReturn(1L);
		when(userDetailsService.getLoggedMusician()).thenReturn(musician_loggedIn);
		when(musicianRepository.findById(2L)).thenReturn(empty());

		MusicianBandDto musicianBandDto = new MusicianBandDto();
		musicianBandDto.setMusicianId(2);
		musicianBandDto.setBandId(1);

		bandService.inviteMusician(musicianBandDto);
	}

	@Test
	public void inviteBackUpMusician_assertCreated() {
		Musician musician_invitee = mock(Musician.class);
		Musician musician_loggedIn = mock(Musician.class);

		Band band = new Band();
		band.setLeader(musician_loggedIn);
		band.setInvitedBackUpMembers(new ArrayList<>());

		MusicianBandDto musicianBandDto = new MusicianBandDto();
		musicianBandDto.setMusicianId(2);
		musicianBandDto.setBandId(1);

		when(bandRepository.findById(1L)).thenReturn(of(band));
		when(musician_loggedIn.getId()).thenReturn(1L);
		when(userDetailsService.getLoggedMusician()).thenReturn(musician_loggedIn);
		when(musicianRepository.findById(2L)).thenReturn(of(musician_invitee));

		bandService.inviteBackUpMusician(musicianBandDto);

		verify(bandRepository).save(band);
		assertTrue(band.getInvitedBackUpMembers().contains(musician_invitee));
	}


	@Test
	public void joinBand_asMember() {
		Musician musician_invitee = mock(Musician.class);
		Musician musician_loggedIn = mock(Musician.class);

		Band band = new Band();

		band.setMembers(newArrayList(musician_invitee));
		band.setBackUpMembers(newArrayList());
		band.setInvited(newArrayList(musician_loggedIn));
		band.setInvitedBackUpMembers(newArrayList());

		when(bandRepository.findById(1L)).thenReturn(of(band));
		when(userDetailsService.getLoggedMusician()).thenReturn(musician_loggedIn);

		bandService.joinBand(1L);

		verify(bandRepository).save(band);
		assertTrue(band.getMembers().contains(musician_loggedIn));
		assertFalse(band.getInvited().contains(musician_loggedIn));
		assertFalse(band.getBackUpMembers().contains(musician_loggedIn));
		assertFalse(band.getInvitedBackUpMembers().contains(musician_loggedIn));
	}

	@Test
	public void joinBand_asBackupMember() {
		Musician musician_invitee = mock(Musician.class);
		Musician musician_loggedIn = mock(Musician.class);

		Band band = new Band();
		band.setMembers(newArrayList(musician_invitee));
		band.setBackUpMembers(newArrayList());
		band.setInvited(newArrayList());
		band.setInvitedBackUpMembers(newArrayList(musician_loggedIn));

		when(bandRepository.findById(1L)).thenReturn(of(band));
		when(userDetailsService.getLoggedMusician()).thenReturn(musician_loggedIn);

		bandService.joinBand(1L);

		verify(bandRepository).save(band);
		assertFalse(band.getMembers().contains(musician_loggedIn));
		assertFalse(band.getInvited().contains(musician_loggedIn));
		assertTrue(band.getBackUpMembers().contains(musician_loggedIn));
		assertFalse(band.getInvitedBackUpMembers().contains(musician_loggedIn));
	}

	@Test(expected = GigerException.class)
	public void joinBand_noBand() {
		when(bandRepository.findById(1L)).thenReturn(empty());

		bandService.joinBand(1L);
	}

	@Test(expected = GigerException.class)
	public void joinBand_NotInvited() {
		Musician musician_invitee = mock(Musician.class);
		Musician musician_loggedIn = mock(Musician.class);

		Band band = new Band();
		band.setMembers(newArrayList(musician_invitee));
		band.setBackUpMembers(newArrayList());
		band.setInvited(newArrayList());
		band.setInvitedBackUpMembers(newArrayList());

		when(bandRepository.findById(1L)).thenReturn(of(band));
		when(userDetailsService.getLoggedMusician()).thenReturn(musician_loggedIn);

		bandService.joinBand(1L);
	}


	@Test
	public void leaveBand() {
	}

	@Test
	public void kickMusician() {
	}

	@Test
	public void kickBackUpMusician() {
	}

	@Test
	public void editProfile() {
	}

	@Test
	public void listInvitations() {
	}

	@Test
	public void changeLeader() {
	}

	@Test
	public void listAvailableBands() {
	}

	@Test
	public void listBands() {
	}

	@Test
	public void getInvitations() {
	}

	@Test
	public void acceptInvitation() {
	}

	@Test
	public void getBand() {
	}

	@Test
	public void cancelInvitation() {
	}
}
