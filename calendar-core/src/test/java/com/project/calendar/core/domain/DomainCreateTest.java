package com.project.calendar.core.domain;

import com.project.calendar.core.domain.entity.Schedule;
import com.project.calendar.core.domain.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomainCreateTest {
    @Test
    void eventCreate(){
        //final User me = new User("name","email","pw");
        //final Schedule taskSchedule = Schedule.task("할일","청소하기", LocalDateTime.now(),me);
        //assertEquals(taskSchedule.getScheduleType(),ScheduleType.TASK); //테스크 타입인지 테스트
        //assertEquals(taskSchedule.toTask().getTitle(),"할일");    //테스크 도메인 객체로 변환후 할일인지 체크
    }
}
