package hr.fer.zemris.opp.giger.controller;

import hr.fer.zemris.opp.giger.config.security.model.RegisterRequestDto;
import hr.fer.zemris.opp.giger.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class RegistrationController {

    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDto registerRequestDto) throws Exception {
        userService.saveUser(registerRequestDto);
        userService.sendEmail(registerRequestDto.getEmail(), registerRequestDto.getUsername());
        return ResponseEntity.ok("Registration ok!");
    }

    @GetMapping(value = "/verification", params = {"token", "username"})
    public String verifyEmail(@RequestParam(name = "token") String token, @RequestParam(name = "username") String username) {
        if (userService.verifyEmail(username, token))
            return "verified-email";
        return "error";
    }



}
