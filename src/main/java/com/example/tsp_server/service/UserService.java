package com.example.tsp_server.service;

import com.example.tsp_server.dto.RegistrationDto;
import com.example.tsp_server.model.User;
import com.example.tsp_server.model.Language;
import com.example.tsp_server.repository.UserRepository;
import com.example.tsp_server.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final LanguageRepository languageRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository, LanguageRepository languageRepository) {
        this.userRepository = userRepository;
        this.languageRepository = languageRepository;
    }

    public Optional<User> authenticate(String login, String password) {
        // Tutaj zakładamy, że hasło w bazie jest przechowywane jako zwykły tekst
        return userRepository.findByLogin(login)
                .filter(user -> user.getPassword().equals(password));
    }

    @Transactional
    public User registerNewUser(RegistrationDto registrationDto) {
        logger.info("Próba zarejestrowania użytkownika przy użyciu nazwy użytkownika: " + registrationDto.getLogin());
        Optional<User> existingUser = userRepository.findByLogin(registrationDto.getLogin());
        if (existingUser.isPresent()) {
            throw new IllegalStateException("Użytkownik już istnieje");
        }
        Language language = languageRepository.findByName(registrationDto.getLanguage())
                .orElseThrow(() -> new IllegalStateException("Nie znaleziono języka"));

        User user = new User();
        user.setLogin(registrationDto.getLogin());
        user.setPassword(registrationDto.getPassword()); // bez szyfrowania
        user.setLanguageId(language.getId());

        return userRepository.save(user);
    }
}
