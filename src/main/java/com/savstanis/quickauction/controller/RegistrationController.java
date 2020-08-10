package com.savstanis.quickauction.controller;

import com.savstanis.quickauction.Routes;
import com.savstanis.quickauction.controller.response.ResponseEntityFactory;
import com.savstanis.quickauction.dto.User.RegisterRequestDto;
import com.savstanis.quickauction.dto.User.UserDto;
import com.savstanis.quickauction.exceptions.UserAlreadyExistAuthenticationException;
import com.savstanis.quickauction.model.User;
import com.savstanis.quickauction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = Routes.REGISTER)
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> register(
            @RequestBody @Valid RegisterRequestDto registerRequestDto,
            BindingResult bindingResult
    ) {
        try {
            if (bindingResult.hasErrors()) {
                return ResponseEntityFactory.getErrorResponse(
                        bindingResult.getFieldError().getField(),
                        bindingResult.getFieldError().getDefaultMessage()
                );
            }
            User user = userService.register(registerRequestDto.toUser());

            return ResponseEntityFactory.getSuccessResponse("user", UserDto.fromUser(user));
        } catch (UserAlreadyExistAuthenticationException e) {
            return ResponseEntityFactory.getErrorResponse(e.getMessage());
        }
    }
}
