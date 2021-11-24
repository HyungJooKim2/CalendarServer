package com.project.calendar.api.controller;

import com.project.calendar.api.dto.LoginReq;
import com.project.calendar.api.dto.SignUpReq;
import com.project.calendar.api.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class LoginApiController {

    private final LoginService loginService;

    @PostMapping("/api/sign-up")
    public ResponseEntity<Void> login(@RequestBody SignUpReq signUpReq, HttpSession httpSession) {
        loginService.signUp(signUpReq, httpSession);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/login")
    public String login(@RequestBody LoginReq loginReq, HttpSession httpSession) {
        loginService.login(loginReq, httpSession);
        return loginService.login(loginReq, httpSession);
    }

    @PostMapping("/api/logout")
    public ResponseEntity<Void> logout(HttpSession httpSession) {
        loginService.logout(httpSession);
        return ResponseEntity.ok().build();
    }
}
