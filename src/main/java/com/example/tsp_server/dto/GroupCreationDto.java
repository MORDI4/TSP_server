package com.example.tsp_server.dto;


import java.util.List;

public class GroupCreationDto {
    private String groupName;
    private List<Integer> userIds;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }
}

