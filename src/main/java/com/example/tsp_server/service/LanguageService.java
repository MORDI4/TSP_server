package com.example.tsp_server.service;

import com.example.tsp_server.model.Language;
import com.example.tsp_server.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<String> findAllLanguageNames() {
        // Pobranie wszystkich języków i zwrócenie listy ich nazw
        return languageRepository.findAll().stream()
                .map(Language::getName)
                .collect(Collectors.toList());
    }

    public Language findByName(String name) {
        // Wyszukanie języka po nazwie, zwrócenie języka lub rzucenie wyjątku, jeśli nie znaleziono
        return languageRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Język nie został odnaleziony :("));
    }
    public Optional<Language> findById(Long id) {
        return languageRepository.findById(id);
    }
}
