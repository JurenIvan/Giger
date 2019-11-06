package hr.fer.zemris.opp.giger.config.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequestDto {

    private String email;
    private String username;
    private String phoneNumber;
    private String password;

    public boolean valid() {
        if (!email.isBlank() && !username.isBlank() && Long.parseLong(phoneNumber) > 0 && password.length() > 8) {
            return true;
        }
        return false;
    }
}
