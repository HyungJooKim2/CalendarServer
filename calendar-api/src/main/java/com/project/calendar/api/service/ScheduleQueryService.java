package com.project.calendar.api.service;

import com.project.calendar.api.dto.AuthUser;
import com.project.calendar.api.dto.DtoConverter;
import com.project.calendar.api.dto.ScheduleDto;
import com.project.calendar.core.domain.entity.repository.EngagementRepository;
import com.project.calendar.core.domain.entity.repository.ScheduleRepository;
import com.project.calendar.core.util.Period;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true) //읽기만 가능
@RequiredArgsConstructor
public class ScheduleQueryService {
    private final ScheduleRepository scheduleRepository;
    private final EngagementRepository engagementRepository;


    public List<ScheduleDto> getSchedulesByDay(LocalDate date, AuthUser authUser) {
        final Period period = Period.of(date,date);
        return getScheduleDtos(authUser, period);
    }


    public List<ScheduleDto> getSchedulesByWeek(LocalDate startOfWeek, AuthUser authUser) {
        final Period period = Period.of(startOfWeek, startOfWeek.plusDays(6));
        return getScheduleDtos(authUser, period);
    }


    public List<ScheduleDto> getSchedulesByMonth(YearMonth yearMonth, AuthUser authUser) {
        final Period period = Period.of(yearMonth.atDay(1), yearMonth.atEndOfMonth());
        return getScheduleDtos(authUser, period);
    }


    private List<ScheduleDto> getScheduleDtos(AuthUser authUser, Period period) {
        return Stream.concat(   //합쳐서 응답을 준다.
                scheduleRepository
                        .findAllByWriter_Id(authUser.getId())
                        .stream()       //특정 날짜와 겹치는것만 필터링을 한다.
                        .filter(schedule -> schedule.isOverlapped(period))
                        .map(schedule -> DtoConverter.toForListDto(schedule)),
                engagementRepository
                        .findAllByAttendeeId(authUser.getId())
                        .stream()
                        .filter(engagement -> engagement.isOverlapped(period))
                        .map(engagement -> DtoConverter.toForListDto(engagement.getSchedule()))
        ).collect(toList());
    }
}
