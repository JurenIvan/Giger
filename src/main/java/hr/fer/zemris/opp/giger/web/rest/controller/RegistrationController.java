package hr.fer.zemris.opp.giger.web.rest.controller;

import hr.fer.zemris.opp.giger.config.security.model.RegisterRequestDto;
import hr.fer.zemris.opp.giger.domain.exception.GigerValidationException;
import hr.fer.zemris.opp.giger.service.PeopleService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/register")
public class RegistrationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
	private PeopleService peopleService;

	@PostMapping
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequestDto registerRequestDto) throws Exception {
		peopleService.saveUser(registerRequestDto);
		peopleService.sendEmail(registerRequestDto.getEmail(), registerRequestDto.getUsername());
		return ResponseEntity.ok("Registration ok!");
	}

	@PostMapping("/resend-verification-email")
	public ResponseEntity<?> resendVerificationEmail(@Valid @RequestBody RegisterRequestDto registerRequestDto) throws Exception {
		peopleService.sendEmail(registerRequestDto.getEmail(), registerRequestDto.getUsername());
		return ResponseEntity.ok("Email has been resent!");
	}

	@GetMapping(value = "/verification", params = {"token", "username"})
	public String verifyEmail(@RequestParam(name = "token") String token, @RequestParam(name = "username") String username) {
		if (peopleService.verifyEmail(username, token))
			return "verified-email";
		return "error";
	}

	@GetMapping("/nickname-available")
	public Boolean isNicknameAvailable(String nickname) {
		return peopleService.isUserNameAvailable(nickname);
	}

	@GetMapping("/email-available")
	public Boolean isEmailAvailable(String email) {
		return peopleService.isEmailAvailable(email);
	}

	private void handleValidation(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			LOGGER.warn("Validation Exception: " + bindingResult.getAllErrors());
			throw new GigerValidationException(bindingResult);
		}
	}
}
