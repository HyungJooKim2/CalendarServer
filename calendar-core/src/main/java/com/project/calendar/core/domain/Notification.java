package com.project.calendar.core.domain;


import com.project.calendar.core.domain.entity.Schedule;
import com.project.calendar.core.domain.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class Notification {
    private Schedule schedule;


    public Notification(Schedule schedule) {
        this.schedule = schedule;
    }
}
