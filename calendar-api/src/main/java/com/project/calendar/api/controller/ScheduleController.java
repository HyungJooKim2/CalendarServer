package com.project.calendar.api.controller;

import com.project.calendar.api.dto.AuthUser;
import com.project.calendar.api.dto.TaskCreateReq;
import com.project.calendar.api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@RequestMapping("/api/schedules")
@RestController
public class ScheduleController {

    private final TaskService taskService;

    @PostMapping("/tasks")
    public ResponseEntity<Void> createTask(@RequestBody TaskCreateReq taskCreateReq,
                                           HttpSession session,
                                           AuthUser authUser
    ) {
//        final Long userId = (Long) session.getAttribute(LOGIN_SESSION_KEY);
//        if (userId == null) {
//            throw new RuntimeException("bad request. no session.");
//        }
        // taskService.create(taskCreateReq, AuthUser.of(userId));
        taskService.create(taskCreateReq,authUser);
        return ResponseEntity.ok().build();
    }

}
