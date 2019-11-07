package hr.fer.zemris.opp.giger.config.security;

import hr.fer.zemris.opp.giger.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        var user = userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("sad"));
        authorityList.add(() -> "USER");

        if (user.getMusician() != null)
            authorityList.add(() -> "MUSICIAN");
        if (user.getOrganizer() != null)
            authorityList.add(() -> "ORGANIZER");

        return new User(user.getUsername(), user.getPasswordHash(), authorityList);
    }
}
