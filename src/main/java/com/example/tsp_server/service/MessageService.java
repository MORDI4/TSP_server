package com.example.tsp_server.service;

import com.example.tsp_server.dto.MessageDto;
import com.example.tsp_server.model.Message;
import com.example.tsp_server.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public MessageDto saveMessage(MessageDto messageDto) {
        Message message = new Message();
        message.setContent(messageDto.getContent());
        message.setSenderId(messageDto.getSenderId());
        message.setGroupId(messageDto.getGroupId());
        message.setTimestamp(messageDto.getTimestamp());
        message = messageRepository.save(message);
        return convertToDto(message);
    }

    public List<MessageDto> getAllMessages() {
        return messageRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<MessageDto> getMessagesBySenderId(Long senderId, Pageable pageable) {
        Page<Message> messagePage = messageRepository.findBySenderId(senderId, pageable);
        return messagePage.getContent().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<MessageDto> getMessagesByGroupId(Long groupId, Pageable pageable) {
        Page<Message> messagePage = messageRepository.findByGroupId(groupId, pageable);
        return messagePage.getContent().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Usunięta druga deklaracja metody convertToDto

    private MessageDto convertToDto(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setContent(message.getContent());
        messageDto.setSenderId(message.getSenderId());
        messageDto.setGroupId(message.getGroupId());
        messageDto.setTimestamp(message.getTimestamp());
        return messageDto;
    }
}
