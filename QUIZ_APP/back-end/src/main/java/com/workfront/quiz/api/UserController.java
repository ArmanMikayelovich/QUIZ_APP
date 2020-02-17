package com.workfront.quiz.api;

import com.workfront.quiz.dto.user.PasswordChangingDto;
import com.workfront.quiz.dto.user.UserInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
public interface UserController {

    UserInfoDto getById(Long id);

    Page<UserInfoDto> search(String text, Pageable pageable);

    Page<UserInfoDto> getAllUsers(Pageable pageable);

    void remove(Long id);

    void update(UserInfoDto user);

    void changePassword(PasswordChangingDto passwordChangingDto);

    UserInfoDto getMe();

    byte[] getOriginalImage(Long userId);

    byte[] getSmallImage(Long userId);

    void uploadImage(MultipartFile image);

}
