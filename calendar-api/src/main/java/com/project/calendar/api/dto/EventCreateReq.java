package com.project.calendar.api.dto;

import lombok.Data;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventCreateReq {
    private final String title;
    private final String description;
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;
    private final List<Long> attendeeIds;   //참석자들의 유저 아이디 리스트

    @ConstructorProperties({"title","description","startAt","endAt","attendeeIds"})
    public EventCreateReq(String title, String description, LocalDateTime startAt, LocalDateTime endAt, List<Long> attendeeIds) {
        this.title = title;
        this.description = description;
        this.startAt = startAt;
        this.endAt = endAt;
        this.attendeeIds = attendeeIds;
    }
}
