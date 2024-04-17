package com.example.tsp_server.controller;

import com.example.tsp_server.dto.RegistrationDto;
import com.example.tsp_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDto registrationDto) {
        userService.registerNewUser(registrationDto);
        return ResponseEntity.ok("Rejestracja przebiegła pomyślnie.");

    }
}
