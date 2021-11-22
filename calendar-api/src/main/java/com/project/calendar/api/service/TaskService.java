package com.project.calendar.api.service;

import com.project.calendar.api.dto.AuthUser;
import com.project.calendar.api.dto.TaskCreateReq;
import com.project.calendar.core.domain.entity.Schedule;
import com.project.calendar.core.domain.entity.repository.ScheduleRepository;
import com.project.calendar.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class TaskService {
    private final UserService userService;
    private final ScheduleRepository scheduleRepository;

    //task정보를 불러와 user조회 후 저장
    @Transactional
    public void create(TaskCreateReq req, AuthUser authUser) {
        final Schedule taskSchedule = Schedule.task(req.getTitle(), req.getDescription(), req.getTaskAt(), userService.getOrThrowById(authUser.getId()));
        scheduleRepository.save(taskSchedule);
    }

}
