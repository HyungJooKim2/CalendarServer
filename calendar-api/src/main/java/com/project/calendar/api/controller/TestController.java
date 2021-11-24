package com.project.calendar.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final JavaMailSender emailSender;

    @GetMapping("/api/mail")
    public @ResponseBody void sendMail() {
        //@FunctionalInterface -> interface에 메소드가 하나 -> 람다식으로 구현 가능
        final MimeMessagePreparator preparator = message -> {
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo("0504pearl@naver.com");
            helper.setSubject("ReadyDance 임시 비밀번호 발급");
            helper.setText("임시 비밀번호 입니다 : xcvxasjkdfnjksa");
        };
        emailSender.send(preparator);
    }

    @GetMapping("test/template")
    public String testTemplate(Model model){
        final Map<String, Object> props = new HashMap<>();
        props.put("title", "타이틀입니다~");
        props.put("calendar","sample@gmail.com");
        props.put("period","언제부터 언제까지");
        props.put("attendees", List.of("test3@mail.io","test2@mail.io","test1@mail.io"));
        props.put("acceptUrl","http://localhost:8003");
        props.put("rejectUrl","http://localhost:8003");
        model.addAllAttributes(props);
        return "engagement-email";
    }


}
