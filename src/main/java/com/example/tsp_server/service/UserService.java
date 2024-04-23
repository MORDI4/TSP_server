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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Service
public class UserService {

    private final UserRepository userRepository;
    private final LanguageRepository languageRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository, LanguageRepository languageRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.languageRepository = languageRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Optional<User> authenticate(String login, String password) {
        return userRepository.findByLogin(login)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()));
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
        user.setPassword(bCryptPasswordEncoder.encode(registrationDto.getPassword()));
        user.setLanguageId(language.getId());

        return userRepository.save(user);
    }
}
