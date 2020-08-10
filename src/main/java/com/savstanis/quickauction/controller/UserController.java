package com.savstanis.quickauction.controller;


import com.savstanis.quickauction.Routes;
import com.savstanis.quickauction.controller.response.ResponseEntityFactory;
import com.savstanis.quickauction.dto.User.ExtendedUserDto;
import com.savstanis.quickauction.dto.User.UserDto;
import com.savstanis.quickauction.model.User;
import com.savstanis.quickauction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = Routes.USER)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity getUser(@PathVariable String username) {
        User user = userService.findByUsername(username);

        if (user == null) {
            return ResponseEntityFactory.getErrorResponse("Invalid username");
        }

        String current_user_name = ((UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()).getUsername();

        if (current_user_name.equals(username)) {
            return ResponseEntityFactory.getSuccessResponse("user", ExtendedUserDto.fromUser(user));
        }
        return ResponseEntityFactory.getSuccessResponse("user", UserDto.fromUser(user));
    }

    @GetMapping
    public ResponseEntity getAll() {
        List<User> users = userService.getAll();
        List<UserDto> userDtos = users.stream().map(UserDto::fromUser).collect(Collectors.toList());

        return ResponseEntityFactory.getSuccessResponse("users", userDtos);
    }
}
