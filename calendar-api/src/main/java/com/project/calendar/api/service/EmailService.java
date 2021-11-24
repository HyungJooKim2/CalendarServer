package com.project.calendar.api.service;


import com.project.calendar.api.dto.EngagementEmailStuff;
import com.project.calendar.core.domain.entity.Engagement;

public interface EmailService {
    void sendEngagement(EngagementEmailStuff stuff);
}
