package com.project.calendar.api.controller;

import com.project.calendar.core.domain.entity.type.RequestReplyType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import static com.project.calendar.api.service.LoginService.LOGIN_SESSION_KEY;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(Model model, HttpSession httpSession,
                        @RequestParam(required = false) String redirect) {

        model.addAttribute("isSignIn", httpSession.getAttribute(LOGIN_SESSION_KEY) != null);
        //로그인을 안되어이쓰면 로그인 페이지로 돌아가게 할 수 있게
        model.addAttribute("redirect", redirect);
        return "index";
    }

    @GetMapping("/events/engagements/{engagementId}")
    public String updateEngagement(@PathVariable Long engagementId,
                                   @RequestParam RequestReplyType type,
                                   Model model,
                                   HttpSession httpSession) {
        model.addAttribute("isSignIn",
                httpSession.getAttribute(LOGIN_SESSION_KEY) != null);
        model.addAttribute("updateType", type.name());
        model.addAttribute("engagementId", engagementId);
        model.addAttribute("path", "/events/engagements/" + engagementId + "?type=" + type.name());
        return "update-event";
    }

}
