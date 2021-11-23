package com.project.calendar.api.dto;

import com.project.calendar.core.domain.ScheduleType;
import lombok.Builder;
import lombok.Data;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

@Data
@Builder
public class EventDto implements ScheduleDto{

    private final Long scheduleId;
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;
    private final String title;
    private final String description;
    private final Long writerId;

    @ConstructorProperties({"scheduleId","startAt","endAt","title","description","writerId"})
    public EventDto(Long scheduleId, LocalDateTime startAt, LocalDateTime endAt, String title, String description, Long writerId) {
        this.scheduleId = scheduleId;
        this.startAt = startAt;
        this.endAt = endAt;
        this.title = title;
        this.description = description;
        this.writerId = writerId;
    }

    @Override
    public ScheduleType getScheduleType() {
        return ScheduleType.EVENT;
    }
}
