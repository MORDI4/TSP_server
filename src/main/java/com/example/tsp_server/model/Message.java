package com.example.tsp_server.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String sender;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private ChatGroup chatGroup;

    public Message() {}

    public Message(String content, String sender, LocalDateTime timestamp, ChatGroup chatGroup) {
        this.content = content;
        this.sender = sender;
        this.timestamp = timestamp;
        this.chatGroup = chatGroup;
    }

    // Getters and Setters
}
