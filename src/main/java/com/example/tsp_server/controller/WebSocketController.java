package com.example.tsp_server.controller;

import com.example.tsp_server.model.ChatGroup;
import com.example.tsp_server.model.Message;
import com.example.tsp_server.repository.ChatGroupRepository;
import com.example.tsp_server.repository.MessageRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatGroupRepository chatGroupRepository;

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public Message sendMessage(String messageContent) {
        String sender = "anonymous";
        Optional<ChatGroup> groupOpt = chatGroupRepository.findById(1L);
        ChatGroup group = groupOpt.orElseThrow(() -> new IllegalArgumentException("Chat group not found"));
        Message message = new Message(messageContent, sender, LocalDateTime.now(), group);
        messageRepository.save(message);
        return message;
    }

    public void sendToSpecificUser(String user, String messageContent) {
        messagingTemplate.convertAndSendToUser(user, "/topic/messages", messageContent);
    }
}
