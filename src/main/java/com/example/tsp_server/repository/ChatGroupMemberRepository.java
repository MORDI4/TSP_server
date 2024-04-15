package com.example.tsp_server.repository;

import com.example.tsp_server.model.ChatGroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatGroupMemberRepository extends JpaRepository<ChatGroupMember, Integer> {
}