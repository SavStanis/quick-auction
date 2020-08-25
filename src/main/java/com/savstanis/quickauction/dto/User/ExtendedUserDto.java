package com.savstanis.quickauction.dto.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.savstanis.quickauction.model.User;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtendedUserDto extends UserDto{

    private String email;

    private Integer balance;

    private String creationDate;

    public static ExtendedUserDto fromUser(User user) {
        ExtendedUserDto userDto = new ExtendedUserDto();

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setBalance(user.getBalance());
        userDto.setCreationDate(user.getCreated().toString());

        return userDto;
    }
}
