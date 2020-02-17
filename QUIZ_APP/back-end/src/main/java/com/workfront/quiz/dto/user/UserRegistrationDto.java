package com.workfront.quiz.dto.user;

import com.workfront.quiz.entity.UserEntity;
import lombok.Data;

@Data
public class UserRegistrationDto {
    public UserEntity toEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(this.email);
        userEntity.setPassword(this.password);
        userEntity.setFirstName(this.firstName);
        userEntity.setLastName(this.lastName);
        return userEntity;
    }

    private String email;

    private String password;

    private String firstName;

    private String lastName;

}
