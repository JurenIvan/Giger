package hr.fer.zemris.opp.giger.service;

import hr.fer.zemris.opp.giger.config.security.model.RegisterRequestDto;
import hr.fer.zemris.opp.giger.domain.Band;
import hr.fer.zemris.opp.giger.domain.Musician;
import hr.fer.zemris.opp.giger.domain.User;
import hr.fer.zemris.opp.giger.repository.BandRepository;
import hr.fer.zemris.opp.giger.repository.MusicianRepository;
import hr.fer.zemris.opp.giger.repository.UserRepository;
import hr.fer.zemris.opp.giger.web.rest.dto.FindUsersDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private EmailSender emailSender;
    private UserRepository userRepository;
    private BandRepository bandRepository;
    private MusicianRepository musicianRepository;

    public UserService(EmailSender emailSender, UserRepository userRepository, BandRepository bandRepository, MusicianRepository musicianRepository) {
        this.emailSender = emailSender;
        this.userRepository = userRepository;
        this.bandRepository = bandRepository;
        this.musicianRepository = musicianRepository;
    }

    @Value("${spring.security.security.BCrypt.secret}")
    private String SECRET_KEY;

    public boolean isUserNameAvailable(String username) {
        return userRepository.findAllByUsername(username).isEmpty();
    }

    public boolean isEmailAvailable(String email) {
        return userRepository.findAllByEmail(email).isEmpty();
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

    public boolean verifyEmail(String username, String token) {
        User user = userRepository.findAllByUsername(username).orElseThrow(() -> new NoSuchElementException());
        if (user.getVerified() != null && user.getVerified() == true)
            return true;
        if (user.getEmail().hashCode() == Integer.parseInt(token))
            user.setVerified(true);
        return userRepository.save(user).getVerified();
    }

    public void sendEmail(String email, String username) {
        emailSender.sendRegistrationConfirmationMessage(email, email.hashCode(), username);
    }

    public List<User> findUsers(FindUsersDto findUsersDto) {
        List<User> usersWithSimilarName = userRepository.findAllByUsernameLike(findUsersDto.getName());
        List<Band> bandsWithSimilarName = bandRepository.findAllByNameLike(findUsersDto.getName());
        List<Musician> musiciansFromBands = musicianRepository.findAllByBandsIn(bandsWithSimilarName);

        usersWithSimilarName.addAll(musiciansFromBands);
        return usersWithSimilarName;
    }
}