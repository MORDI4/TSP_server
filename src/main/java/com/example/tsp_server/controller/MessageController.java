package com.example.tsp_server.controller;

import com.example.tsp_server.dto.MessageDto;
import com.example.tsp_server.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public MessageDto sendMessage(@RequestBody MessageDto messageDto) {
        return messageService.saveMessage(messageDto);
    }

    @GetMapping
    public List<MessageDto> getAllMessages() {
        return messageService.getAllMessages();
    }
}
