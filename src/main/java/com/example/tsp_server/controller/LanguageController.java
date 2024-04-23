package com.example.tsp_server.controller;

import com.example.tsp_server.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
