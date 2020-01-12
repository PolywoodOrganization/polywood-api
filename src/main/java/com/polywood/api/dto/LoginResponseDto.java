package com.polywood.api.dto;

import com.polywood.api.model.UsersEntity;

public class LoginResponseDto {

    private String token;
    private UsersEntity user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

}
