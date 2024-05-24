package com.example.tsp_server.controller;

import com.example.tsp_server.dto.MessageDto;
import com.example.tsp_server.model.ChatMessage;
import com.example.tsp_server.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ChatMessageController {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @PostMapping("/chat_messages")
    public ResponseEntity<String> sendMessage(@RequestBody MessageDto messageDto) {
        // Tworzenie nowej wiadomości
        ChatMessage newMessage = new ChatMessage(messageDto.getMessage(), messageDto.getGroupId(), 1L); // Zakładając, że masz identyfikator nadawcy

        // Zapisz wiadomość w bazie danych
        chatMessageRepository.save(newMessage);

        return ResponseEntity.ok().body("Message received and saved");
    }
}
