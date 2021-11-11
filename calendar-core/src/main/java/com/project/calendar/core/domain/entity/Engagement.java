package com.project.calendar.core.domain.entity;

import com.project.calendar.core.domain.Event;
import com.project.calendar.core.domain.RequestStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Table(name = "engagements")
@Entity
public class Engagement extends BaseEntity{

    @JoinColumn(name = "schedule_id")
    @ManyToOne
    private Schedule schedule;

    @JoinColumn(name = "attendee_id")
    @ManyToOne
    private User attendee;
    private RequestStatus requestStatus;  //약속을 거절했는지 수락했는지


}
