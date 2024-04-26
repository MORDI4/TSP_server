
package com.example.tsp_server.dto;

import java.time.LocalDateTime;

public class MessageDto {
    private Long id;
    private String content;
    private Long senderId;
    private Long groupId;
    private LocalDateTime timestamp;

    public MessageDto() {
    }

    public MessageDto(String content, Long senderId, Long groupId, LocalDateTime timestamp) {
        this.content = content;
        this.senderId = senderId;
        this.groupId = groupId;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
