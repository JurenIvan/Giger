package hr.fer.zemris.opp.giger.service;

import hr.fer.zemris.opp.giger.config.security.model.RegisterRequestDto;
import hr.fer.zemris.opp.giger.domain.*;
import hr.fer.zemris.opp.giger.domain.enums.Role;
import hr.fer.zemris.opp.giger.domain.exception.GigerException;
import hr.fer.zemris.opp.giger.repository.*;
import hr.fer.zemris.opp.giger.web.rest.dto.FindUsersDto;
import hr.fer.zemris.opp.giger.web.rest.dto.PersonPreviewDto;
import hr.fer.zemris.opp.giger.web.rest.dto.ReviewPreviewDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.collect.Lists.newArrayList;
import static hr.fer.zemris.opp.giger.domain.exception.ErrorCode.*;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class PeopleService {

	private EmailSender emailSender;
	private SystemPersonRepository systemPersonRepository;
	private PersonRepository peopleRepository;
	private ReviewRepository reviewRepository;
	private BandRepository bandRepository;
	private InstrumentRepository instrumentRepository;


	public boolean isUserNameAvailable(String username) {
		return peopleRepository.findByUsername(username).isEmpty();
	}

	public boolean isEmailAvailable(String email) {
		return systemPersonRepository.findByEmail(email).isEmpty();
	}

	public void saveUser(RegisterRequestDto registerRequestDto) throws Exception {
		if (!isEmailAvailable(registerRequestDto.getEmail())) {
			throw new GigerException(EMAIL_ALREADY_IN_USE);
		}
		if (!isUserNameAvailable(registerRequestDto.getUsername())) {
			throw new GigerException(USERNAME_ALREADY_IN_USE);
		}
		if (registerRequestDto.getPassword().length() < 1) {
			throw new GigerException(INVALID_PASSWORD);
		}

		SystemPerson systemPerson = new SystemPerson();
		systemPerson.setEmail(registerRequestDto.getEmail());
		systemPerson.setPasswordHash(BCrypt.hashpw(registerRequestDto.getPassword(), BCrypt.gensalt(10)));
		systemPerson.setVerified(false);
		systemPerson.setLocked(false);
		systemPerson.addRole(Role.PERSON);
		systemPerson = systemPersonRepository.save(systemPerson);

		Person person = new Person();
		person.setId(systemPerson.getId());
		person.setPhoneNumber(registerRequestDto.getPhoneNumber());
		person.setUsername(registerRequestDto.getUsername());
		peopleRepository.save(person);
	}

	public boolean verifyEmail(String username, String token) {
		Person person = peopleRepository.findByUsername(username).orElseThrow(() -> new GigerException(NO_SUCH_USER));
		SystemPerson systemPerson = systemPersonRepository.findById(person.getId()).get();

		if (systemPerson.getVerified() != null && systemPerson.getVerified())
			return true;
		if (systemPerson.getEmail().hashCode() == Integer.parseInt(token))
			systemPerson.setVerified(true);
		return systemPersonRepository.save(systemPerson).getVerified();
	}

	public void sendEmail(String email, String username) {
		emailSender.sendRegistrationConfirmationMessage(email, email.hashCode(), username);
	}

	public List<Person> findPeople(FindUsersDto findUsersDto) {
		List<Person> usernameMatches = newArrayList();
		List<Person> bandNameMatches = newArrayList();

		if (findUsersDto.getName() != null) {
			usernameMatches = peopleRepository.findByUsernameContaining(findUsersDto.getName());
		}
		if (findUsersDto.getBand() != null) {
			bandNameMatches = peopleRepository.findAllByIdIn(
					bandRepository.findAllByNameContaining(findUsersDto.getBand()).stream()
							.map(Band::getMembers)
							.flatMap(List::stream)
							.map(Musician::getId)
							.collect(toList()));
		}
		Set<Person> setOfPeople = new HashSet<>();
		Stream.concat(usernameMatches.stream(), bandNameMatches.stream()).forEach(setOfPeople::add);

		return setOfPeople.stream().map(e -> new Person(e.getId(), e.getUsername(), "", e.getPictureUrl())).collect(toList());
	}

	public List<ReviewPreviewDto> getReviews(Long personId) {
		Person person = peopleRepository.findById(personId).orElseThrow(() -> new GigerException(NO_SUCH_USER));
		return reviewRepository.findAllByAuthor(person).stream().map(Review::toDto).collect(toList());
	}

	public PersonPreviewDto findPerson(Long personId) {
		return peopleRepository.findById(personId).orElseThrow(() -> new GigerException(NO_SUCH_USER)).toDto();
	}

	public List<Instrument> getAllInstrument() {
		return instrumentRepository.findAll();
	}
}