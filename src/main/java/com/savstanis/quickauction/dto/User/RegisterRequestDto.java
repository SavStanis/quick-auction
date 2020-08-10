package com.savstanis.quickauction.dto.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.savstanis.quickauction.model.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RegisterRequestDto {

    @Size(min = 4, max = 100,  message = "Username size must be between 4 and 100")
    private String username;

    @Email(message = "Invalid email")
    @Size(max = 100,  message = "Email must contain less then 100 characters")
    private String email;

    @Size(min = 8, message = "Password must contain at least 8 characters")
    private String password;

    public User toUser() {
        User user = new User();

        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }

}
