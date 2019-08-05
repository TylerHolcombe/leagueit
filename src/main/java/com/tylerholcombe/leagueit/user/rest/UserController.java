package com.tylerholcombe.leagueit.user.rest;

import com.tylerholcombe.leagueit.user.data.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public Long addNewUser(@RequestBody UserDto request) {
        //TODO: validate request info
        return userService.createUser(request);
    }
}
