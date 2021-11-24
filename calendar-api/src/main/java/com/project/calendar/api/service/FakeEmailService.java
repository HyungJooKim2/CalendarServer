package com.project.calendar.api.service;

import com.project.calendar.api.dto.EngagementEmailStuff;
import com.project.calendar.core.domain.entity.Engagement;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Service;

@Profile("dev")
@Service
public class FakeEmailService implements EmailService {
    @Override
    public void sendEngagement(EngagementEmailStuff e) {
        System.out.println("메일발송 - attendee: " + e.getSubject());
    }
}
