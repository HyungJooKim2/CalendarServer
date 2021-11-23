package com.project.calendar.api.dto;

import com.project.calendar.core.domain.ScheduleType;
import lombok.Builder;
import lombok.Data;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

@Data
@Builder
public class NotificationDto implements ScheduleDto{

    private final Long scheduleId;
    private final LocalDateTime notifyAt;
    private final String title;
    private final Long writerId;

    @ConstructorProperties({"scheduleId","notifyAt","title","writerId"})
    public NotificationDto(Long scheduleId, LocalDateTime notifyAt, String title, Long writerId) {
        this.scheduleId = scheduleId;
        this.notifyAt = notifyAt;
        this.title = title;
        this.writerId = writerId;
    }

    @Override
    public ScheduleType getScheduleType() {
        return ScheduleType.NOTIFICATION;
    }
}
