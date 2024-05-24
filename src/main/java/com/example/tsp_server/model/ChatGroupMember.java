package com.example.tsp_server.model;

import jakarta.persistence.*;

@Entity
public class ChatGroupMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private ChatGroup chatGroup;

    private Integer userId;

    public ChatGroupMember() {}

    public ChatGroupMember(Long chatGroupId, Integer userId) {
        this.chatGroup = new ChatGroup();
        this.chatGroup.setId(chatGroupId);
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChatGroup getChatGroup() {
        return chatGroup;
    }

    public void setChatGroup(ChatGroup chatGroup) {
        this.chatGroup = chatGroup;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
