package hr.fer.zemris.opp.giger.service;

import hr.fer.zemris.opp.giger.config.security.UserDetailsServiceImpl;
import hr.fer.zemris.opp.giger.domain.Band;
import hr.fer.zemris.opp.giger.domain.Gig;
import hr.fer.zemris.opp.giger.domain.Location;
import hr.fer.zemris.opp.giger.domain.Musician;
import hr.fer.zemris.opp.giger.domain.exception.GigerException;
import hr.fer.zemris.opp.giger.repository.*;
import hr.fer.zemris.opp.giger.web.rest.dto.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static java.time.LocalDateTime.now;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.util.Lists.newArrayList;
import static org.junit.jupiter.api.Assertions.*;
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
		Musician musician = mock(Musician.class);
		BandCreationDto bandCreationDto = new BandCreationDto();

		bandCreationDto.setName("name");

		when(userDetailsService.getLoggedMusician()).thenReturn(musician);
		when(bandRepository.findByName("name")).thenReturn(empty());

		bandService.createBand(bandCreationDto);
		Band band = new Band();
		band.setName("name");
		band.setLeader(musician);
		band.setMembers(newArrayList(musician));
		band.setFormedDate(now().withNano(0));
		band.setLeader(musician);

		verify(bandRepository).save(band);
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
		Musician musician = mock(Musician.class);
		Band band = new Band();
		band.setMembers(newArrayList(musician));
		band.setBackUpMembers(newArrayList());

		when(bandRepository.findById(1L)).thenReturn(of(band));
		when(userDetailsService.getLoggedMusician()).thenReturn(musician);
		when(userDetailsService.getLoggedMusician().getId()).thenReturn(1L);

		bandService.leaveBand(1L);

		verify(bandRepository).save(band);
		assertFalse(band.getMembers().contains(musician));
	}

	@Test
	public void kickMusician() {
		Musician musician_kicked = mock(Musician.class);
		Musician musician_loggedIn = mock(Musician.class);

		Band band = new Band();
		band.setId(1L);
		band.setLeader(musician_loggedIn);
		band.setMembers(newArrayList(musician_kicked, musician_loggedIn));
		band.setBackUpMembers(new ArrayList<>());

		KickDto kickDto = new KickDto();
		kickDto.setBandId(1L);
		kickDto.setMusicianId(1L);

		when(userDetailsService.getLoggedMusician()).thenReturn(musician_loggedIn);
		when(musician_loggedIn.getId()).thenReturn(2L);
		when(musician_kicked.getId()).thenReturn(1L);
		when(bandRepository.findById(1L)).thenReturn(of(band));

		bandService.kickMusician(kickDto);

		verify(bandRepository).save(band);
		assertFalse(band.getMembers().contains(musician_kicked));
	}

	@Test
	public void editProfile_asMember() {
		Musician musician = mock(Musician.class);
		BandEditProfileDto bandEditProfileDto = new BandEditProfileDto();
		Location location = mock(Location.class);

		bandEditProfileDto.setBio("bio");
		bandEditProfileDto.setBandId(1L);
		bandEditProfileDto.setLocation(location);
		bandEditProfileDto.setRemovePostIds(newArrayList());

		Band band = new Band();
		band.setMembers(newArrayList(musician));
		band.setPosts(newArrayList());

		when(userDetailsService.getLoggedMusician()).thenReturn(musician);
		when(bandRepository.findById(1L)).thenReturn(of(band));

		band.setBio("bio");
		band.setHome(location);

		bandService.editProfile(bandEditProfileDto);
		verify(bandRepository).save(band);
	}

	@Test
	public void editProfile_asLeader() {
		Musician musician = mock(Musician.class);
		Musician musicianOther = mock(Musician.class);
		BandEditProfileDto bandEditProfileDto = new BandEditProfileDto();
		Location location = mock(Location.class);

		bandEditProfileDto.setBio("bio");
		bandEditProfileDto.setBandId(1L);
		bandEditProfileDto.setLocation(location);
		bandEditProfileDto.setRemovePostIds(newArrayList());

		Band band = new Band();
		band.setMembers(newArrayList(musicianOther));
		band.setLeader(musician);
		band.setPosts(newArrayList());

		when(userDetailsService.getLoggedMusician()).thenReturn(musician);
		when(bandRepository.findById(1L)).thenReturn(of(band));

		band.setBio("bio");
		band.setHome(location);

		bandService.editProfile(bandEditProfileDto);
		verify(bandRepository).save(band);
	}

	@Test(expected = GigerException.class)
	public void editProfile_notAuthorized() {
		Musician musician = mock(Musician.class);
		Musician musicianOther = mock(Musician.class);

		Band band = new Band();
		band.setMembers(newArrayList(musicianOther));
		band.setLeader(musicianOther);

//		when(userDetailsService.getLoggedMusician()).thenReturn(musician);
//		when(bandRepository.findById(1L)).thenReturn(of(band));

		bandService.editProfile(mock(BandEditProfileDto.class));
	}

	@Test
	public void listInvitations() {
	}

	@Test
	public void changeLeader() {
		Musician musician_loggedIn = mock(Musician.class);
		Musician new_leader = mock(Musician.class);

		MusicianBandDto musicianBandDto = new MusicianBandDto();
		musicianBandDto.setBandId(1L);
		musicianBandDto.setMusicianId(1L);

		Band band = new Band();
		band.setLeader(musician_loggedIn);
		band.setMembers(newArrayList(musician_loggedIn, new_leader));
		band.setBackUpMembers(newArrayList());

		when(bandRepository.findById(1L)).thenReturn(of(band));
		when(userDetailsService.getLoggedMusician()).thenReturn(musician_loggedIn);
		when(musicianRepository.findById(1L)).thenReturn(of(new_leader));
		when(musician_loggedIn.getId()).thenReturn(1L);

		bandService.changeLeader(musicianBandDto);
		assertEquals(band.getLeader(), new_leader);
	}

	@Test
	public void listAvailableBands() {
	}

	@Test
	public void listBands() {
	}

	@Test
	public void getInvitations() {
		Musician musician_loggedIn = mock(Musician.class);
		Gig gig1 = mock(Gig.class);
		Gig gig2 = mock(Gig.class);
		GigPreviewDto gigPreviewDto1 = mock(GigPreviewDto.class);
		GigPreviewDto gigPreviewDto2 = mock(GigPreviewDto.class);

		Band band = new Band();
		band.setLeader(musician_loggedIn);
		band.setMembers(newArrayList(musician_loggedIn));
		band.setInvitationGigs(newArrayList(gig1, gig2));

		when(bandRepository.findById(1L)).thenReturn(of(band));
		when(userDetailsService.getLoggedMusician()).thenReturn(musician_loggedIn);
		when(gig1.toDto()).thenReturn(gigPreviewDto1);
		when(gig2.toDto()).thenReturn(gigPreviewDto2);

		var result = bandService.getInvitations(1L);
		assertEquals(newArrayList(gigPreviewDto1, gigPreviewDto2), result);
	}

	@Test(expected = GigerException.class)
	public void getInvitations_notMemberNorLeader() {
		Musician musician_loggedIn = mock(Musician.class);
		Musician new_leader = mock(Musician.class);

		Band band = new Band();
		band.setLeader(new_leader);
		band.setMembers(newArrayList(new_leader));

		when(bandRepository.findById(1L)).thenReturn(of(band));
		when(userDetailsService.getLoggedMusician()).thenReturn(musician_loggedIn);

		bandService.getInvitations(1L);
	}

	@Test(expected = GigerException.class)
	public void getInvitations_notValidBand() {
		when(bandRepository.findById(1L)).thenReturn(empty());
		bandService.getInvitations(1L);
	}


	@Test
	public void acceptInvitation() {
	}

	@Test
	public void getBand() {
		Band band = mock(Band.class);
		BandDto bandDto = mock(BandDto.class);

		when(bandRepository.findById(1L)).thenReturn(of(band));
		when(band.toDto()).thenReturn(bandDto);

		assertEquals(bandService.getBand(1L),bandDto);
	}

	@Test
	public void cancelInvitation() {
	}
}
