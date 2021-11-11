package com.project.calendar.core.domain;




import com.project.calendar.core.domain.entity.Engagement;
import com.project.calendar.core.domain.entity.Schedule;
import com.project.calendar.core.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
public class Event {
    private Schedule schedule;

    public Event(Schedule schedule){
        this.schedule = schedule;
    }
}
