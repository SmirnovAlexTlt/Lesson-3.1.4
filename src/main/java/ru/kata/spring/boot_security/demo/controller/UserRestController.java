package ru.kata.spring.boot_security.demo.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.UserService.UserService;
import ru.kata.spring.boot_security.demo.model.UserModel;

import java.security.Principal;

@RestController
@RequestMapping("/apiUser")
public class UserRestController {


    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserModel> showUser (Principal principal){
        UserModel userModel = userService.showUserByEmail(principal.getName());
        return new ResponseEntity<>(userModel, HttpStatus.OK);
    }
}
