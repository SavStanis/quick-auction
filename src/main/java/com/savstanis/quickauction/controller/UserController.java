package com.savstanis.quickauction.controller;


import com.savstanis.quickauction.Routes;
import com.savstanis.quickauction.controller.response.ResponseEntityFactory;
import com.savstanis.quickauction.dto.Lot.ExtendedLotDto;
import com.savstanis.quickauction.dto.User.BalanceReplDto;
import com.savstanis.quickauction.dto.User.ExtendedUserDto;
import com.savstanis.quickauction.dto.User.UserDto;
import com.savstanis.quickauction.exceptions.BadRequestException;
import com.savstanis.quickauction.model.Lot;
import com.savstanis.quickauction.model.User;
import com.savstanis.quickauction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping(value = "/{user_id}")
    public ResponseEntity getUser(
            @PathVariable(name = "user_id") String userId
    ) {
        try {
            User user = userService.findById(Long.parseLong(userId));
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

    @Transactional
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

            userService.replenishUserBalance(Long.parseLong(user_id), balanceReplDto.getSum());

            return ResponseEntityFactory.getSuccessResponse();
        } catch (NumberFormatException | BadRequestException e) {
            return ResponseEntityFactory.getErrorResponse("Invalid user id");
        }
    }

    @GetMapping(value = "/{user_id}/lots")
    public ResponseEntity getLotsByUserId(
            @PathVariable(name = "user_id") String userId
    ) {
        try {
            List<Lot> lotList = userService.findLotsBySellerId(Long.parseLong(userId));
            List<ExtendedLotDto> responseData = lotList.stream()
                    .map(ExtendedLotDto::fromLot)
                    .collect(Collectors.toList());

            return ResponseEntityFactory.getSuccessResponse("lots", responseData);
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
