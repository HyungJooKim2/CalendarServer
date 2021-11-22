package com.project.calendar.api.service;

import com.project.calendar.core.domain.entity.Engagement;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("dev")
@Service
public class FakeEmailService implements com.project.calendar.api.service.EmailService {
    @Override
    public void sendEngagement(Engagement e) {
        System.out.println("메일발송 - attendee: " + e.getAttendee().getEmail() + ", scheduleId: " + e.getEvent().getId());
    }
}
