package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.config.security.model.RegisterRequestDto;
import hr.fer.zemris.opp.giger.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/register")
public class RegistrationController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDto registerRequestDto) throws Exception {
        userService.saveUser(registerRequestDto);
        userService.sendEmail(registerRequestDto.getEmail(), registerRequestDto.getUsername());
        return ResponseEntity.ok("Registration ok!");
    }

    @PostMapping("/resend-verification-email")
    public ResponseEntity<?> resendVerificationEmail(@RequestBody RegisterRequestDto registerRequestDto) throws Exception {
        userService.sendEmail(registerRequestDto.getEmail(), registerRequestDto.getUsername());
        return ResponseEntity.ok("Email has been resent!");
    }

    @GetMapping(value = "/verification", params = {"token", "username"})
    public String verifyEmail(@RequestParam(name = "token") String token, @RequestParam(name = "username") String username) {
        if (userService.verifyEmail(username, token))
            return "verified-email";
        return "error";
    }

    @GetMapping("/nickname-available")
    public boolean isNicknameAvailable(String nickname) {
        return userService.isUserNameAvailable(nickname);
    }
}
