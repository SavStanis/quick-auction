package com.savstanis.quickauction.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.savstanis.quickauction.model.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private long id;

    @Size(min = 4, max = 100)
    private String username;

    @Email
    @Size(max = 100)
    private String email;

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());

        return userDto;
    }
}