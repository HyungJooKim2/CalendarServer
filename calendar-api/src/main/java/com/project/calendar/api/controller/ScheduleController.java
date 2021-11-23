package com.project.calendar.api.controller;

import com.project.calendar.api.dto.*;
import com.project.calendar.api.service.EventService;
import com.project.calendar.api.service.NotificationService;
import com.project.calendar.api.service.ScheduleQueryService;
import com.project.calendar.api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/api/schedules")
@RestController
public class ScheduleController {
                  //조회만 담당하는 서비스
    private final ScheduleQueryService scheduleQueryService;
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
    public ResponseEntity<Void> createEvent(@Valid @RequestBody EventCreateReq eventCreateReq,
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

    @GetMapping("/day")
    public List<ScheduleDto> getSchedulesByDay(
            AuthUser authUser,             //query일 경우에는 format을 정해주어야 한다.)
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return scheduleQueryService.getSchedulesByDay(date == null ? LocalDate.now() : date, authUser);
    }

    @GetMapping("/week")
    public List<ScheduleDto> getSchedulesByWeek(
            AuthUser authUser,                                              //이 날짜를 기준으로 일주일 기준으로 걸쳐있는 계획을 가져옴
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startOfWeek
    ) {
        return scheduleQueryService.getSchedulesByWeek(startOfWeek == null ? LocalDate.now() : startOfWeek, authUser);
    }

    @GetMapping("/month")
    public List<ScheduleDto> getSchedulesByMonth(
            AuthUser authUser,                                         //해당 달의 일정을
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM") String yearMonth
    ) {
        return scheduleQueryService.getSchedulesByMonth(yearMonth == null ? YearMonth.now() : YearMonth.parse(yearMonth), authUser);
    }

}
