package com.project.calendar.core.domain;




import com.project.calendar.core.domain.entity.Engagement;
import com.project.calendar.core.domain.entity.Schedule;
import com.project.calendar.core.domain.entity.User;
import com.project.calendar.core.util.Period;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class Event {
    private final Schedule schedule;

    public Event(Schedule schedule) {
        this.schedule = schedule;
    }

    public Long getId() {
        return this.schedule.getId();
    }
    public LocalDateTime getStartAt() {
        return schedule.getStartAt();
    }

    public LocalDateTime getEndAt() {
        return schedule.getEndAt();
    }

    public User getWriter() {
        return this.schedule.getWriter();
    }

    public boolean isOverlapped(LocalDateTime startAt, LocalDateTime endAt) {
        return this.getStartAt().isBefore(endAt) && startAt.isBefore(this.getEndAt());
    }

    public String getTitle(){
        return this.schedule.getTitle();
    }

    public Period getPeriod() {
        return Period.of(this.schedule.getStartAt(), this.schedule.getEndAt());
    }
}
