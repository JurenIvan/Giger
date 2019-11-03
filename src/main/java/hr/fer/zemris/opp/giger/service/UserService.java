package hr.fer.zemris.opp.giger.service;

import hr.fer.zemris.opp.giger.config.security.model.RegisterRequestDto;
import hr.fer.zemris.opp.giger.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public boolean isUserNameAvailable(String username) {
        return userRepository.findAllByUsername(username).isEmpty();
    }

    public boolean isEmailAvailable(String email) {
        return userRepository.findAllByEmail( email).isEmpty();
    }

    public void saveUser(RegisterRequestDto registerRequestDto) throws Exception {
        if (!isEmailAvailable(registerRequestDto.getEmail())) {
            throw new Exception("email not available");
        }
        if (!isUserNameAvailable(registerRequestDto.getUsername())) {
            throw new Exception("username not available");
        }

        var user = new hr.fer.zemris.opp.giger.domain.User();

        user.setUsername(registerRequestDto.getUsername());
        user.setEmail(registerRequestDto.getEmail());
        user.setPasswordHash(BCrypt.hashpw(registerRequestDto.getPassword(), BCrypt.gensalt(12)));
        user.setPhoneNumber(registerRequestDto.getPhoneNumber());

        userRepository.save(user);
    }
}