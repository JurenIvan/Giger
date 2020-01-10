package hr.fer.zemris.opp.giger.config.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequestDto {

    @NotBlank
    private String email;
    @NotBlank
    private String username;
    private String phoneNumber;
    @Min(8)
    private String password;

    public boolean valid() {
        if (!email.isBlank() && !username.isBlank() && Long.parseLong(phoneNumber) > 0 && password.length() > 8) {
            return true;
        }
        return false;
    }
}
