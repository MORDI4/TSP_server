package com.example.tsp_server.service;

import com.example.tsp_server.model.User;
import com.example.tsp_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tsp_server.dto.RegistrationDto;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> authenticate(String login, String password) {
        return userRepository.findByLogin(login)
                .filter(user -> user.getPassword().equals(password));
    }

    public void registerNewUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setName(registrationDto.getUsername());
        user.setPassword(registrationDto.getPassword());
        user.setLanguageId(getLanguageIdByName(registrationDto.getLanguage())); //
        user.setLogin(registrationDto.getUsername());

        userRepository.save(user);
    }


    private Integer getLanguageIdByName(String languageName) {

        return null;
    }
}
