package com.example.tsp_server.controller;

import com.example.tsp_server.model.ChatGroup;
import com.example.tsp_server.service.ChatGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class ChatGroupController {
    @Autowired
    private ChatGroupService chatGroupService;

    @GetMapping
    public List<ChatGroup> getAllGroups() {
        return chatGroupService.findAllGroups();
    }
}
