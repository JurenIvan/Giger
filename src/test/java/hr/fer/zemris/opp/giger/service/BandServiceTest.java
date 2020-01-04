package hr.fer.zemris.opp.giger.service;

import hr.fer.zemris.opp.giger.config.security.UserDetailsServiceImpl;
import hr.fer.zemris.opp.giger.repository.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


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

	@Test
	public void inviteMusician() {
	}

	@Test
	public void inviteBackUpMusician() {
	}

	@Test
	public void joinBand() {
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
