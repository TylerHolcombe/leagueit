package com.tylerholcombe.leagueit.user.data;

import com.tylerholcombe.leagueit.user.rest.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Long createUser(UserModel userModel) {
        // TODO: hash and salt password
        User user = new User(userModel.getUsername(), userModel.getPassword());
        return userRepository.save(user).getId();
    }
}
