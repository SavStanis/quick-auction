package com.savstanis.quickauction.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AuthRequestDto {

    @Size(min = 4, max = 100, message = "Username size must be between 4 and 100")
    private final String username;

    @Size(min = 8, message = "Password must contain at least 8 characters")
    private final String password;
}
