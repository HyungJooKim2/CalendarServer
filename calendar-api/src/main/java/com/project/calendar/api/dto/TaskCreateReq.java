package com.project.calendar.api.dto;

import lombok.Data;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

@Data
public class TaskCreateReq {
    private final String title;
    private final String description;
    private final LocalDateTime taskAt;

    @ConstructorProperties({"title","description","taskAt"})
    public TaskCreateReq(String title, String description, LocalDateTime taskAt) {
        this.title = title;
        this.description = description;
        this.taskAt = taskAt;
    }
}
