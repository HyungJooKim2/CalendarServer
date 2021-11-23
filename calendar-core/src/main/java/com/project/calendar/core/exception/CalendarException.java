package com.project.calendar.core.exception;

import lombok.Getter;

@Getter
//Exception 은 오버헤드를 많이 던진다.. 꼭 필요한 것만 추가
public class CalendarException extends RuntimeException {

    private final ErrorCode errorCode;

    public CalendarException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
