package com.example.tsp_server.controller;

import com.example.tsp_server.dto.GroupCreationDto;
import com.example.tsp_server.model.ChatGroup;
import com.example.tsp_server.model.ChatGroupMember;
import com.example.tsp_server.repository.ChatGroupMemberRepository;
import com.example.tsp_server.repository.ChatGroupRepository;
import com.example.tsp_server.service.ChatGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class ChatGroupController {
    @Autowired
    private ChatGroupService chatGroupService;

    @Autowired
    private ChatGroupRepository groupRepository;

    @Autowired
    private ChatGroupMemberRepository chatGroupMemberRepository;

    @GetMapping
    public List<ChatGroup> getAllGroups() {
        return chatGroupService.findAllGroups();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createGroup(@RequestBody GroupCreationDto groupDto) {
        ChatGroup newGroup = new ChatGroup();
        newGroup.setName(groupDto.getGroupName());
        groupRepository.save(newGroup);

        for (Integer userId : groupDto.getUserIds()) {
            ChatGroupMember member = new ChatGroupMember(newGroup.getId(), userId);
            chatGroupMemberRepository.save(member);
        }

        return ResponseEntity.ok("Grupa utworzona pomyślnie");
    }

}
