package com.workfront.quiz.service;

import com.workfront.quiz.dto.user.PasswordChangingDto;
import com.workfront.quiz.dto.user.UserInfoDto;
import com.workfront.quiz.dto.user.UserRegistrationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

    UserInfoDto findById(Long id);

    UserInfoDto findByEmail(String email);

    Page<UserInfoDto> searchByName(String name, Pageable pageable);

    Page<UserInfoDto> getAllUsers(Pageable pageable);

    void remove(Long id);

    void update(UserInfoDto user);

    void updatePassword(PasswordChangingDto passwordChangingDto);

    UserInfoDto register(UserRegistrationDto registrationDto);

    Long getMe();

    String generateToken(String email);

    void activateByEmailToken(String token);

    byte[] getOriginalImage(Long userId);

    byte[] getSmallImage(Long userId);

    void saveImage(MultipartFile image,Long userId);
}
