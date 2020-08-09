package com.savstanis.quickauction.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.savstanis.quickauction.model.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RegisterRequestDto {

    @Size(min = 4, max = 100)
    private String username;

    @Email
    @Size(max = 100)
    private String email;

    @Size(min = 8, max = 300)
    private String password;

    public User toUser() {
        User user = new User();

        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }

}
