package com.project.calendar.core.service;

import com.project.calendar.core.domain.entity.User;
import com.project.calendar.core.domain.entity.repository.UserRepository;
import com.project.calendar.core.dto.UserCreateReq;
import com.project.calendar.core.exception.CalendarException;
import com.project.calendar.core.exception.ErrorCode;
import com.project.calendar.core.util.Encryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Encryptor bcryptEncryptor;
    private final UserRepository userRepository;

    @Transactional
    public User create(UserCreateReq req) {
        userRepository.findByEmail(req.getEmail())
                .ifPresent(u -> {  //회원가입인데 있으면 안되니
                   // throw new CalendarException(ErrorCode.USER_NOT_FOUND);
                    throw new RuntimeException("user already existed!");
                });
        return userRepository.save(User.builder()
                .name(req.getName())
                .password(bcryptEncryptor.encrypt(req.getPassword()))
                .email(req.getEmail())
                .birthday(req.getBirthday())
                .build());
    }

    //넘겨받은 정보와 매칭이 되는지
    @Transactional
    public Optional<User> findPwMatchUser(String email, String password) {
        return userRepository.findByEmail(email)
                .map(u -> u.isMatched(bcryptEncryptor, password) ? u : null);
    }

    @Transactional
    public User getOrThrowById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new CalendarException(ErrorCode.USER_NOT_FOUND));
    }

}
