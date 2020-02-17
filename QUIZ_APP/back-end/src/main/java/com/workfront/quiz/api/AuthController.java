package com.workfront.quiz.api;

import com.workfront.quiz.dto.user.LoginRequestDto;
import com.workfront.quiz.dto.user.UserRegistrationDto;

public interface AuthController {
    void register(UserRegistrationDto registrationDto);

    String login(LoginRequestDto loginRequestDto);
}
