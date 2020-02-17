package com.workfront.quiz.security;

import com.workfront.quiz.entity.UserEntity;
import com.workfront.quiz.repository.UserRepository;
import com.workfront.quiz.security.jwt.JwtUser;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Primary
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(email);

        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            return new JwtUser(userEntity.getId(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getRoles());
        }
        throw new UsernameNotFoundException(email);
    }

}
