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
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@RestController
@RequestMapping("/register")
public class RegistrationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
	private PeopleService peopleService;

	@PostMapping
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequestDto registerRequestDto, BindingResult bindingResult) throws Exception {
		LOGGER.info("RegisterUser: " + registerRequestDto);
		handleValidation(bindingResult);
		peopleService.saveUser(registerRequestDto);
		peopleService.sendEmail(registerRequestDto.getEmail(), registerRequestDto.getUsername());
		return ResponseEntity.ok("Registration ok!");
	}

	@PostMapping("/resend-verification-email")
	public ResponseEntity<?> resendVerificationEmail(@Valid @RequestBody RegisterRequestDto registerRequestDto, BindingResult bindingResult) throws Exception {
		LOGGER.info("ResendVerificationEmail: " + registerRequestDto);
		handleValidation(bindingResult);
		peopleService.sendEmail(registerRequestDto.getEmail(), registerRequestDto.getUsername());
		return ResponseEntity.ok("Email has been resent!");
	}

	@GetMapping(value = "/verification", params = {"token", "username"})
	public String verifyEmail(@RequestParam(name = "token") String token, @RequestParam(name = "username") String username, BindingResult bindingResult) {
		LOGGER.info("VerifyEmail: " + token + " username: " + username);
		handleValidation(bindingResult);
		if (peopleService.verifyEmail(username, token))
			return "verified-email";
		return "error";
	}

	@GetMapping("/nickname-available")
	public Boolean isNicknameAvailable(@PathVariable @NotBlank String nickname, BindingResult bindingResult) {
		LOGGER.info("VerifyEmail: " + nickname);
		handleValidation(bindingResult);
		return peopleService.isUserNameAvailable(nickname);
	}

	@GetMapping("/email-available")
	public Boolean isEmailAvailable(@PathVariable @NotBlank String email, BindingResult bindingResult) {
		LOGGER.info("IsEmailAvailable: " + email);
		handleValidation(bindingResult);
		return peopleService.isEmailAvailable(email);
	}

	private void handleValidation(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			LOGGER.warn("Validation Exception: " + bindingResult.getAllErrors());
			throw new GigerValidationException(bindingResult);
		}
	}
}
