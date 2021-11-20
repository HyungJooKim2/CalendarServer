package com.project.calendar.core.domain.entity;

import com.project.calendar.core.domain.Event;
import com.project.calendar.core.domain.Notification;
import com.project.calendar.core.domain.ScheduleType;
import com.project.calendar.core.domain.Task;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//이 안에서만 빌더 사용 가능
@Builder(access = AccessLevel.PRIVATE)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "schedules")
@Entity
public class Schedule extends BaseEntity{

    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String title;
    private String description;

    @JoinColumn(name = "writer_id") //객체와 테이블의 키를 매핑
    @ManyToOne //다대일 관계 writer는 여러 writer_id를 가지므로
    private User writer;

    //enum 값 그대로 문자열로 db에 넣을 수 있는 방법
    @Enumerated(EnumType.STRING)
    private ScheduleType scheduleType;


    //이벤트 생성 요청이 왔을때 helper class
    public static Schedule event(String title, String description, LocalDateTime startAt, LocalDateTime endAt, User writer){
        return Schedule.builder()
                .title(title)
                .description(description)
                .startAt(startAt)
                .endAt(endAt)
                .writer(writer)
                .scheduleType(ScheduleType.EVENT)
                .build();
    }

    //이벤트 생성 요청이 왔을때 helper class
    public static Schedule task(String title, String description, LocalDateTime taskAt, User writer){
        return Schedule.builder()
                .title(title)
                .description(description)
                .startAt(taskAt)
                .writer(writer)
                .scheduleType(ScheduleType.TASK)
                .build();
    }

    //이벤트 생성 요청이 왔을때 helper class
    public static Schedule notification(String title, LocalDateTime notifyAt,User writer){
        return Schedule.builder()
                .title(title)
                .startAt(notifyAt)
                .writer(writer)
                .scheduleType(ScheduleType.NOTIFICATION)
                .build();
    }
    /*
    도메인 레이어까지는 스케쥴이 등장하는데
    스케쥴이 결국 이벤트로 변하고 다른 도메인 클래스들로 변경이 되어야 하는데
    거기에 필요한 매소드들
     */
    public Task toTask(){
        return new Task(this);
    }

    public Event toEvent(){
        return new Event(this);
    }

    public Notification toNOtification(){
        return new Notification(this);
    }
}
