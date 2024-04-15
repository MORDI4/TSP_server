package com.example.tsp_server.service;

import com.example.tsp_server.model.User;
import com.example.tsp_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
                .filter(user -> user.getPassword().equals(password)); // prosty sposób, należy użyć lepszego mechanizmu hashowania
    }

}
