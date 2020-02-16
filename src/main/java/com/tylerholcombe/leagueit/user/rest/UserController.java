package com.tylerholcombe.leagueit.user.rest;

import com.tylerholcombe.leagueit.user.data.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public Long addNewUser(@RequestBody UserDto request) {
        try {
            return userService.createUser(request);
        } catch (DataIntegrityViolationException violation) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists.");
        }
    }
}
