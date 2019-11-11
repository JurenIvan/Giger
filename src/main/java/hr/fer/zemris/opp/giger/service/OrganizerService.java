package hr.fer.zemris.opp.giger.service;

import hr.fer.zemris.opp.giger.config.security.UserDetailsServiceImpl;
import hr.fer.zemris.opp.giger.domain.Organizer;
import hr.fer.zemris.opp.giger.domain.User;
import hr.fer.zemris.opp.giger.repository.OrganizerRepository;
import hr.fer.zemris.opp.giger.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizerService {

    private OrganizerRepository organizerRepository;
    private UserRepository userRepository;
    private UserDetailsServiceImpl userDetailsService;

    public void createOrganizer(String managerName) {
        User user = userDetailsService.getLoggedUser();
        if (user.getOrganizer() != null)
            return;
        persistData(user, new Organizer(user.getId(), managerName, null, user));
    }

    public void editOrganizer(String managerName) {
        User user = userDetailsService.getLoggedUser();
        Organizer organizer = user.getOrganizer();
        organizer.setManagerName(managerName);
        organizerRepository.save(organizer);
    }

    private void persistData(User user, Organizer organizer) {
        organizerRepository.save(organizer);
        user.setOrganizer(organizer);
        userRepository.save(user);
    }
}
