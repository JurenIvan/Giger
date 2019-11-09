package hr.fer.zemris.opp.giger.config.security;

import hr.fer.zemris.opp.giger.config.errorHandling.GigerException;
import hr.fer.zemris.opp.giger.repository.SystemPersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static hr.fer.zemris.opp.giger.config.errorHandling.ErrorCode.NO_SUCH_USER_EXCEPTION;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private SystemPersonRepository systemPersonRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return systemPersonRepository.findByEmail(email).orElseThrow(() -> new GigerException(NO_SUCH_USER_EXCEPTION));
    }
}
