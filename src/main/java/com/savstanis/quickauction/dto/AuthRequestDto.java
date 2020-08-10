package com.savstanis.quickauction.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AuthRequestDto {

    @Email(message = "Invalid email")
    @Size(max = 100,  message = "Email must contain less then 100 characters")
    private final String email;

    @Size(min = 8, message = "Password must contain at least 8 characters")
    private final String password;
}
