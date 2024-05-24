package com.example.tsp_server.controller;

import com.example.tsp_server.dto.LoginCredentials;
import com.example.tsp_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<?> loginUser(@RequestBody LoginCredentials loginCredentials, HttpSession session) {
        return userService.authenticate(loginCredentials.getLogin(), loginCredentials.getPassword())
                .map(user -> {
                    session.setAttribute("user", user);
                    return ResponseEntity.ok(Map.of(
                            "message", "Użytkownik zalogowany",
                            "userId", user.getUserId(),
                            "login", user.getLogin()
                    ));
                })
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Błąd logowania")));
    }
}
