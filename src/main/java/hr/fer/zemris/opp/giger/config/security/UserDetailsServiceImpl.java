package hr.fer.zemris.opp.giger.config.security;

import hr.fer.zemris.opp.giger.config.errorHandling.GigerException;
import hr.fer.zemris.opp.giger.domain.Musician;
import hr.fer.zemris.opp.giger.domain.Organizer;
import hr.fer.zemris.opp.giger.domain.Person;
import hr.fer.zemris.opp.giger.domain.SystemPerson;
import hr.fer.zemris.opp.giger.repository.MusicianRepository;
import hr.fer.zemris.opp.giger.repository.OrganizerRepository;
import hr.fer.zemris.opp.giger.repository.PersonRepository;
import hr.fer.zemris.opp.giger.repository.SystemPersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static hr.fer.zemris.opp.giger.config.errorHandling.ErrorCode.NO_ROLE_DATA_PRESENT;
import static hr.fer.zemris.opp.giger.config.errorHandling.ErrorCode.NO_SUCH_USER_EXCEPTION;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private SystemPersonRepository systemPersonRepository;
    private PersonRepository personRepository;
    private MusicianRepository musicianRepository;
    private OrganizerRepository organizerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return systemPersonRepository.findByEmail(email).orElseThrow(() -> new GigerException(NO_SUCH_USER_EXCEPTION));
    }

    public Person getLoggedPerson() {
        return personRepository.findById(getLoggedInUserId()).orElseThrow(() -> new GigerException(NO_SUCH_USER_EXCEPTION));
    }

    public Musician getLoggedMusician() {
        return musicianRepository.findById(getLoggedInUserId()).orElseThrow(() -> new GigerException(NO_ROLE_DATA_PRESENT));
    }

    public boolean isLoggedUserMusician() {
        return musicianRepository.findById(getLoggedInUserId()).isPresent();
    }

    public boolean isLoggedUserOrganizer() {
        return organizerRepository.findById(getLoggedInUserId()).isPresent();
    }

    public Organizer getLoggedOrganizer() {
        return organizerRepository.findById(getLoggedInUserId()).orElseThrow(() -> new GigerException(NO_ROLE_DATA_PRESENT));
    }

    public Long getLoggedInUserId() {
        return ((SystemPerson) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
}