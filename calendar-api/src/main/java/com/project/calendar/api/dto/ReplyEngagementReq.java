package com.project.calendar.api.dto;

import com.project.calendar.core.domain.entity.type.RequestReplyType;
import lombok.Data;

@Data
public class ReplyEngagementReq {
    private final RequestReplyType type; // REJECT, ACCEPT

    public ReplyEngagementReq(RequestReplyType type) {
        this.type = type;
    }
}
