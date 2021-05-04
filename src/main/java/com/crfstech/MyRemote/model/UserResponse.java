package com.crfstech.MyRemote.model;

import lombok.Data;

@Data
public class UserResponse {
    private String token;
    private String message;

    public UserResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }
}
