package hr.fer.zemris.opp.giger.config.security;

import hr.fer.zemris.opp.giger.config.security.model.AuthenticationRequestDto;
import hr.fer.zemris.opp.giger.config.security.model.AuthenticationResponseDto;
import hr.fer.zemris.opp.giger.domain.exception.ErrorCode;
import hr.fer.zemris.opp.giger.domain.exception.GigerException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static hr.fer.zemris.opp.giger.domain.exception.ErrorCode.INVALID_PASSWORD;

@RestController
@AllArgsConstructor
public class AuthenticateController {

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtTokenUtil;
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequest) throws Exception {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new GigerException(INVALID_PASSWORD);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponseDto(jwt));
    }
}
