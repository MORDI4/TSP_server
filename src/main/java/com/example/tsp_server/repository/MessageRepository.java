package com.example.tsp_server.repository;

import com.example.tsp_server.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Page<Message> findBySenderId(Long senderId, Pageable pageable);
    Page<Message> findByGroupId(Long groupId, Pageable pageable);
}
