package com.example.tsp_server.service;

import com.example.tsp_server.dto.RegistrationDto;
import com.example.tsp_server.model.User;
import com.example.tsp_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Optional<User> authenticate(String login, String password) {

        return userRepository.findByLogin(login)
                .filter(user -> bCryptPasswordEncoder.matches(password, user.getPassword()));
    }

    public User registerNewUser(RegistrationDto registrationDto) {
        if (userRepository.findByLogin(registrationDto.getUsername()).isPresent()) {
            return null;
        }

        User user = new User();
        user.setLogin(registrationDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registrationDto.getPassword()));
        user.setLanguageId(getLanguageIdByName(registrationDto.getLanguage()));

        return userRepository.save(user);
    }

    private Integer getLanguageIdByName(String languageName) {

        return null;
    }
}
