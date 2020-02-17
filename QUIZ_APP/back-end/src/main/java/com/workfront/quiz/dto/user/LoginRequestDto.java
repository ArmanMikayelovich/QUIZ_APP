package com.workfront.quiz.dto.user;

import com.workfront.quiz.entity.UserEntity;
import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;

    private String password;
    @Override
    public String toString() {
        return "LoginRequestDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    public UserEntity toEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(this.email);
        userEntity.setPassword(this.password);
        return userEntity;
    }
}
