package hr.fer.zemris.opp.giger.service;

import hr.fer.zemris.opp.giger.config.security.UserDetailsServiceImpl;
import hr.fer.zemris.opp.giger.domain.Organizer;
import hr.fer.zemris.opp.giger.domain.exception.GigerException;
import hr.fer.zemris.opp.giger.repository.OrganizerRepository;
import hr.fer.zemris.opp.giger.repository.SystemPersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static hr.fer.zemris.opp.giger.domain.enums.Role.MUSICIAN;
import static hr.fer.zemris.opp.giger.domain.exception.ErrorCode.NO_SUCH_USER;
import static hr.fer.zemris.opp.giger.domain.exception.ErrorCode.ORGANIZER_ALREADY_EXISTS;

@Service
@AllArgsConstructor
public class OrganizerService {

	private OrganizerRepository organizerRepository;
	private UserDetailsServiceImpl userDetailsService;
	private SystemPersonRepository systemPersonRepository;

	public void createOrganizer(String managerName) {
		if (userDetailsService.isLoggedUserOrganizer())
			throw new GigerException(ORGANIZER_ALREADY_EXISTS);

		var loggedInUserId = userDetailsService.getLoggedInUserId();
		var systemPerson = systemPersonRepository.findById(loggedInUserId).orElseThrow(() -> new GigerException(NO_SUCH_USER));
		systemPerson.addRole(MUSICIAN);

		systemPersonRepository.save(systemPerson);
		organizerRepository.save(new Organizer(userDetailsService.getLoggedInUserId(), managerName));
	}

	public void editOrganizer(String managerName) {
		Organizer organizer = userDetailsService.getLoggedOrganizer();
		organizer.setManagerName(managerName);
		organizerRepository.save(organizer);
	}
}
