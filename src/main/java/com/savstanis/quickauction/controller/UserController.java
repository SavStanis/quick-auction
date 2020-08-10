package com.savstanis.quickauction.controller;


import com.savstanis.quickauction.Routes;
import com.savstanis.quickauction.controller.response.ResponseEntityFactory;
import com.savstanis.quickauction.dto.UserDto;
import com.savstanis.quickauction.model.User;
import com.savstanis.quickauction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public ResponseEntity getAll() {
        List<User> users = userService.getAll();
        List<UserDto> userDtos = users.stream().map(user -> UserDto.fromUser(user)).collect(Collectors.toList());

        return ResponseEntityFactory.getSuccessResponse("users", userDtos);
    }
}
