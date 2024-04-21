package com.example.tsp_server.controller;

import com.example.tsp_server.service.UserService;
import com.example.tsp_server.model.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.tsp_server.dto.LoginCredentials;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginCredentials loginCredentials) {
        return userService.authenticate(loginCredentials.getLogin(), loginCredentials.getPassword())
                .map(user -> ResponseEntity.ok(Map.of("message", "Użytkownik zalogowany")))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Błąd logowania")));
    }
}
