package com.project.calendar.api.service;

import com.project.calendar.api.dto.LoginReq;
import com.project.calendar.api.dto.SignUpReq;
import com.project.calendar.core.domain.entity.User;
import com.project.calendar.core.dto.UserCreateReq;
import com.project.calendar.core.exception.CalendarException;
import com.project.calendar.core.exception.ErrorCode;
import com.project.calendar.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@RequiredArgsConstructor    //서비스는 하위에 여러가지 빈들을 주입받게 되는데 타입에 맞는 생성자를 자동 생성
public class LoginService {

    private final UserService userService;
    public final static String LOGIN_SESSION_KEY = "USER_ID";
    //회원가입용 객체를 받아 create -> 세션에 값을 넣고 끝
    @Transactional
    public void signUp(SignUpReq signUpReq, HttpSession session) {
        /*
         1. UserService 에 create 요청 (이미 존재하는 유저 검증은 UserService 담당)
         2. session 에 담고 리턴
         */
        final User user = userService.create(new UserCreateReq(
                signUpReq.getName(),
                signUpReq.getEmail(),
                signUpReq.getPassword(),
                signUpReq.getBirthday()));
        //http 요청 안에 포함되는 세션정보를 가져와 setAttribute를 사용하여 key, value를 담음
        session.setAttribute(LOGIN_SESSION_KEY, user.getId());
    }

    //로그인 reuqest를 받아 세션에 값이 있었는지 확인 후 아이디 비밀번호가 맞는지 확인 후 세션에 추가
    @Transactional
    public void login(LoginReq loginReq, HttpSession session) {
        /*
        세션 값이 있으면 리턴
        없으면 비밀번호 체크 후 로그인
         */
        //이미 세션에 있다면 (이미 로그인이 되어있으면) 아이디가 나옴
        final Long userId = (Long) session.getAttribute(LOGIN_SESSION_KEY);
        if (userId != null) {
            return;
        }                                        //아매알, 패스워드가 매치되는 유저가 있는지 있다면 가져옴
        final Optional<User> user = userService.findPwMatchUser(loginReq.getEmail(), loginReq.getPassword());
        if (user.isPresent()) {
            //있다면 세션에 추가
            session.setAttribute(LOGIN_SESSION_KEY, user.get().getId());
        } else {
            throw new CalendarException(ErrorCode.PASSWORD_NOT_MATCH);
        }
    }

    //로그아웃 세션 키를 통해 remove
    public void logout(HttpSession session) {
        /*
        세션 제거
         */
        session.removeAttribute(LOGIN_SESSION_KEY);
    }

}
