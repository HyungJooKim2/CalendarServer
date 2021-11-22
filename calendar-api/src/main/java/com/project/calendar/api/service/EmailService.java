package com.project.calendar.api.service;


import com.project.calendar.core.domain.entity.Engagement;

public interface EmailService {
    void sendEngagement(Engagement e);
}
