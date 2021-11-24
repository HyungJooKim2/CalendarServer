package com.project.calendar.api.service;

import com.project.calendar.api.dto.AuthUser;
import com.project.calendar.core.domain.RequestStatus;
import com.project.calendar.core.domain.entity.Engagement;
import com.project.calendar.core.domain.entity.repository.EngagementRepository;
import com.project.calendar.core.domain.entity.type.RequestReplyType;
import com.project.calendar.core.exception.CalendarException;
import com.project.calendar.core.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EngagementService {

    private final EngagementRepository engagementRepository;

    @Transactional
    public RequestStatus update(AuthUser authUser, Long engagementId, RequestReplyType type) {
        return engagementRepository.findById(engagementId)
                .filter(Engagement::isRequested) //isRequested 상태인 것만
                .filter(e -> e.getAttendee().getId().equals(authUser.getId())) //실제로 요청한 auth유저의 아이디와 같아야만
                .map(e -> e.reply(type)) //status 를 적용?..
                .orElseThrow(() -> new CalendarException(ErrorCode.BAD_REQUEST))
                .getStatus();
    }
}
