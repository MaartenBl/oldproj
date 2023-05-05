package com.user.userservice.controller;

import com.user.userservice.model.UserModel;
import com.user.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/user") // URL's start
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping(path = "/add") // Map ONLY POST Requests
    public  @ResponseBody
    String addNewUser(@RequestParam String displayName
            , @RequestParam String email
            , @RequestParam String password) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        String role = "standard";

        UserModel user = new UserModel();
        user.setDisplayName(displayName);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder
                .encode(password));
        user.setRole(role);
        userRepository.save(user);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<UserModel> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
}
