package com.example.tsp_server.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class ChatGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "chatGroup")
    private Set<ChatGroupMember> members;

    public ChatGroup() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ChatGroupMember> getMembers() {
        return members;
    }

    public void setMembers(Set<ChatGroupMember> members) {
        this.members = members;
    }
}
