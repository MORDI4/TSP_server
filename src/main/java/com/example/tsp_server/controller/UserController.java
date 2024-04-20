package com.example.tsp_server.controller;

import com.example.tsp_server.dto.RegistrationDto;
import com.example.tsp_server.service.UserService;
import com.example.tsp_server.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final LanguageService languageService;

    @Autowired
    public UserController(UserService userService, LanguageService languageService) {
        this.userService = userService;
        this.languageService = languageService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDto registrationDto) {
        userService.registerNewUser(registrationDto);
        return ResponseEntity.ok("Rejestracja przebiegła pomyślnie.");
    }

    @GetMapping("/languages")
    public ResponseEntity<List<String>> getLanguages() {
        List<String> languages = languageService.findAllLanguageNames();
        return ResponseEntity.ok(languages);
    }
}
