package com.project.calendar.api.service;

import com.project.calendar.api.dto.AuthUser;
import com.project.calendar.api.dto.NotificationCreateReq;
import com.project.calendar.core.domain.entity.Schedule;
import com.project.calendar.core.domain.entity.User;
import com.project.calendar.core.domain.entity.repository.ScheduleRepository;
import com.project.calendar.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final ScheduleRepository scheduleRepository;
    private final UserService userService;

    @Transactional
    public void create(NotificationCreateReq req, AuthUser authUser) {
        final User user = userService.findByUserId(authUser.getId());
        final List<LocalDateTime> notifyAtList = req.getRepeatTimes();
        notifyAtList.forEach(notifyAt ->{
            final Schedule notificationSchedule =
                    Schedule.notification(
                            req.getTitle(),
                            notifyAt,
                            user);
            scheduleRepository.save(notificationSchedule);
        });
    }
}
