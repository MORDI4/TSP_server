package com.example.tsp_server.controller;

import com.example.tsp_server.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {

    private final LanguageService languageService;

    @Autowired
    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping("/names")
    public List<String> getAllLanguageNames() {
        return languageService.findAllLanguageNames();
    }

    @PutMapping("/users/{userId}/language")
    public ResponseEntity<?> updateLanguage(@PathVariable Integer userId, @RequestBody Integer languageId) {
        boolean isUpdated = languageService.updateUserLanguage(userId, languageId);
        if(isUpdated) {
            return ResponseEntity.ok().build(); // Zaktualizowano pomyślnie
        } else {
            return ResponseEntity.badRequest().body("Nie udało się zaktualizować języka lub język jest taki sam.");
        }
    }
}