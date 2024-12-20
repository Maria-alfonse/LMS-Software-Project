package com.example.lms.dto;

import com.example.lms.model.user_related.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserData {
    private int id;
    private String name;

    public UserData(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }
}
