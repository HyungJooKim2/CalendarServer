package com.project.calendar.api.dto;

import com.project.calendar.core.domain.ScheduleType;
import lombok.Builder;
import lombok.Data;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

@Data
@Builder
public class TaskDto implements ScheduleDto{

    private final Long scheduleId;
    private final LocalDateTime taskAt;
    private final String title;
    private final String description;
    private final Long writerId;

    @ConstructorProperties({"scheduleId","taskAt","title","description","writerId"})
    public TaskDto(Long scheduleId, LocalDateTime taskAt, String title, String description, Long writerId) {
        this.scheduleId = scheduleId;
        this.taskAt = taskAt;
        this.title = title;
        this.description = description;
        this.writerId = writerId;
    }

    @Override
    public ScheduleType getScheduleType() {
        return ScheduleType.TASK;
    }
}
