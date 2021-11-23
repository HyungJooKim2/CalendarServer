package com.project.calendar.api.service;

import com.project.calendar.api.dto.AuthUser;
import com.project.calendar.api.dto.EventCreateReq;
import com.project.calendar.core.domain.RequestStatus;
import com.project.calendar.core.domain.entity.Engagement;
import com.project.calendar.core.domain.entity.Schedule;
import com.project.calendar.core.domain.entity.repository.EngagementRepository;
import com.project.calendar.core.domain.entity.repository.ScheduleRepository;
import com.project.calendar.core.exception.CalendarException;
import com.project.calendar.core.exception.ErrorCode;
import com.project.calendar.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final UserService userService;
    private final ScheduleRepository scheduleRepository;
    private final EngagementRepository engagementRepository;
    private final FakeEmailService emailService;

    @Transactional
    public void create(EventCreateReq req, AuthUser authUser) {
        // attendees(이벤트 참여자)의 다른 스케쥴 시간과 겹치지 않는지 체크
        // 성공 시 이메일 발송(일단 인터페이스 호출)
        final List<Engagement> engagementList =
                engagementRepository.findAllByAttendeeIdInAndSchedule_EndAtAfter(req.getAttendeeIds(),
                        req.getStartAt());
        if (engagementList
                .stream()
                //참석자 아이디에 포함이 되어있거나
                .anyMatch(e -> req.getAttendeeIds().contains(e.getAttendee().getId())
                        //수락상태인게 있으면
                        && e.getStatus() == RequestStatus.ACCEPTED
                        //리스트 중에  기간이 겹치는게 있거나
                        && e.getEvent().isOverlapped(req.getStartAt(), req.getEndAt())
                )) {
            throw new CalendarException(ErrorCode.EVENT_CREATE_OVERLAPPED_PERIOD);
        }
        final Schedule eventSchedule = Schedule.event(req.getTitle(), req.getDescription(), req.getStartAt(), req.getEndAt(), userService.findByUserId(authUser.getId()));
        scheduleRepository.save(eventSchedule);
        req.getAttendeeIds().stream()
                .map(userService::findByUserId)
                .forEach(user -> {
                    final Engagement e = engagementRepository.save(new Engagement(eventSchedule, user));
                    emailService.sendEngagement(e);
                });
    }
}
