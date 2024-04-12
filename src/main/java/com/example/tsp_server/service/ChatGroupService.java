package com.example.tsp_server.service;

import com.example.tsp_server.model.ChatGroup;
import com.example.tsp_server.repository.ChatGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatGroupService {
    @Autowired
    private ChatGroupRepository chatGroupRepository;

    public List<ChatGroup> findAllGroups() {
        return chatGroupRepository.findAll();
    }
}
