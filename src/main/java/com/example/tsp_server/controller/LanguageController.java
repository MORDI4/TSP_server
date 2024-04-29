package com.example.tsp_server.controller;

import com.example.tsp_server.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

import com.example.tsp_server.model.Language;
import com.example.tsp_server.dto.LanguageUpdateDTO;
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
    public ResponseEntity<?> updateLanguage(@PathVariable("userId") Integer userId, @RequestBody LanguageUpdateDTO languageUpdate) {
        Integer languageId = languageUpdate.getLanguageId();
        boolean isUpdated = languageService.updateUserLanguage(userId, languageId);
        if(isUpdated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Nie udało się zaktualizować języka lub język jest taki sam.");
        }
    }

    @GetMapping("/names/id")
    public ResponseEntity<?> getLanguageIdByName(@RequestParam("name") String name) {
        Language language = languageService.findByName(name);
        return ResponseEntity.ok(language.getId());
    }
}