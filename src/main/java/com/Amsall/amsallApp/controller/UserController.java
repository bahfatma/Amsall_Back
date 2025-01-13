package com.Amsall.amsallApp.controller;

import com.Amsall.amsallApp.exceptions.EmailAlreadyExistsException;
import com.Amsall.amsallApp.model.Users;
import com.Amsall.amsallApp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        return ResponseEntity.status(409).body(ex.getMessage());
    }
    @PostMapping("/auth")
    public void addUser(@RequestBody Users user ){
        userService.saveUser(user);
    }
}
