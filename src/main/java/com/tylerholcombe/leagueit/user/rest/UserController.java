package com.tylerholcombe.leagueit.user.rest;

import com.tylerholcombe.leagueit.user.data.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public Long addNewUser(@RequestBody UserDto request) {
        //TODO: validate unique username (throw better exception) -- catch/rethrow DB exception
        return userService.createUser(request);
    }
}
