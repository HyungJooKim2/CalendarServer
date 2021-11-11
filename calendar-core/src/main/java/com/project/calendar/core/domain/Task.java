package com.project.calendar.core.domain;


import com.project.calendar.core.domain.entity.Schedule;
import com.project.calendar.core.domain.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Task {

    private Schedule schedule;

    public Task(Schedule schedule) {
        this.schedule = schedule;
    }

    public String getTitle(){
        return schedule.getTitle();
    }
}
