package com.example.tsp_server.repository;

import com.example.tsp_server.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
    // Lista wszystkich języków jest już dostępna przez dziedziczenie z JpaRepository

    // Dodanie metody do wyszukiwania języka po nazwie
    Optional<Language> findByName(String name);
}
