package com.example.tsp_server.service;

import com.example.tsp_server.model.Language;
import com.example.tsp_server.model.User;
import com.example.tsp_server.repository.LanguageRepository;
import com.example.tsp_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;
    private final UserRepository userRepository;

    @Autowired
    public LanguageService(LanguageRepository languageRepository, UserRepository userRepository) {
        this.languageRepository = languageRepository;
        this.userRepository = userRepository;
    }

    public List<String> findAllLanguageNames() {
        return languageRepository.findAll().stream()
                .map(Language::getName)
                .collect(Collectors.toList());
    }

    public Language findByName(String name) {
        return languageRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Język nie został odnaleziony."));
    }

    public Optional<Language> findById(Long id) {
        return languageRepository.findById(id);
    }

    @Transactional
    public boolean updateUserLanguage(Integer userId, Integer languageId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (!user.getLanguageId().equals(languageId)) {
                user.setLanguageId(languageId);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }
}