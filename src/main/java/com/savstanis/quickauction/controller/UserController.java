package com.savstanis.quickauction.controller;


import com.savstanis.quickauction.Routes;
import com.savstanis.quickauction.controller.response.ResponseEntityFactory;
import com.savstanis.quickauction.dto.User.BalanceReplDto;
import com.savstanis.quickauction.dto.User.ExtendedUserDto;
import com.savstanis.quickauction.dto.User.UserDto;
import com.savstanis.quickauction.exceptions.BadRequestException;
import com.savstanis.quickauction.model.User;
import com.savstanis.quickauction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = Routes.USER)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{user_id}")
    public ResponseEntity getUser(
            @PathVariable String user_id
    ) {
        try {
            User user = userService.findById(Long.parseLong(user_id));
            if (user == null) {
                throw new BadRequestException();
            }

            String current_user_name = getCurrentUserUsername();

            if (current_user_name.equals(user.getUsername())) {
                return ResponseEntityFactory.getSuccessResponse("user", ExtendedUserDto.fromUser(user));
            }
            return ResponseEntityFactory.getSuccessResponse("user", UserDto.fromUser(user));
        } catch (NumberFormatException | BadRequestException e) {
            return ResponseEntityFactory.getErrorResponse("Invalid user id");
        }
    }

    @PostMapping(value = "/{user_id}/balance")
    public ResponseEntity replenishBalance(
            @PathVariable String user_id,
            @RequestBody @Valid BalanceReplDto balanceReplDto,
            BindingResult bindingResult
            ) {
        try {
            if (bindingResult.hasErrors()) {
                return ResponseEntityFactory.getErrorResponse(
                        bindingResult.getFieldError().getField(),
                        bindingResult.getFieldError().getDefaultMessage()
                );
            }

            userService.replenishBalance(Long.parseLong(user_id), balanceReplDto.getSum());

            return ResponseEntityFactory.getSuccessResponse();
        } catch (NumberFormatException | BadRequestException e) {
            return ResponseEntityFactory.getErrorResponse("Invalid user id");
        }
    }

    private String getCurrentUserUsername() {
        return ((UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()).getUsername();
    }

//    @GetMapping
//    public ResponseEntity getAll() {
//        List<User> users = userService.getAll();
//        List<UserDto> userDtos = users.stream().map(UserDto::fromUser).collect(Collectors.toList());
//
//        return ResponseEntityFactory.getSuccessResponse("users", userDtos);
//    }
}
