package com.tylerholcombe.leagueit.user.rest;

import com.tylerholcombe.leagueit.user.data.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, path = "/signup")
    public Long addNewUser(@RequestBody UserModel request) {
        //TODO: validate request info
        return userService.createUser(request);
    }

    private void validateAddNewUser(UserModel userModel) {

    }
}
