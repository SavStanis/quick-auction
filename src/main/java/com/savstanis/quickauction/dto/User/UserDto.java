package com.savstanis.quickauction.dto.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.savstanis.quickauction.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private long id;

    private String username;

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());

        return userDto;
    }
}