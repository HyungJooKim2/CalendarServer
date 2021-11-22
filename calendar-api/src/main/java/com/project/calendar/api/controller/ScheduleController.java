package com.project.calendar.api.controller;

import com.project.calendar.api.dto.AuthUser;
import com.project.calendar.api.dto.EventCreateReq;
import com.project.calendar.api.dto.NotificationCreateReq;
import com.project.calendar.api.dto.TaskCreateReq;
import com.project.calendar.api.service.EventService;
import com.project.calendar.api.service.NotificationService;
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

    private final EventService eventService;
    private final TaskService taskService;
    private final NotificationService notificationService;

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

    @PostMapping("/events")
    public ResponseEntity<Void> createEvent(@RequestBody EventCreateReq eventCreateReq,
                                           AuthUser authUser
    ) {
        eventService.create(eventCreateReq,authUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/notifications")
    public ResponseEntity<Void> createNotifications(@RequestBody NotificationCreateReq notificationCreateReq,
                                            AuthUser authUser
    ) {
        notificationService.create(notificationCreateReq ,authUser);
        return ResponseEntity.ok().build();
    }

}
