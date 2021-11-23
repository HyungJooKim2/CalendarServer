package com.project.calendar.api.dto;

import com.project.calendar.core.domain.type.TimeUnit;
import com.project.calendar.core.exception.CalendarException;
import com.project.calendar.core.exception.ErrorCode;
import lombok.Data;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Data
public class NotificationCreateReq {
    private final String title;
    private final LocalDateTime notifyAt;
    private final RepeatInfo repeatInfo;  //얼마마다 할지 (기간)

    @ConstructorProperties({"title","notifyAt","repeatInfo"})
    public NotificationCreateReq(String title, LocalDateTime notifyAt, RepeatInfo repeatInfo) {
        this.title = title;
        this.notifyAt = notifyAt;
        this.repeatInfo = repeatInfo;
    }

    public List<LocalDateTime> getRepeatTimes() {
        if (repeatInfo == null) {
            //없으면 하나만 리턴
            return Collections.singletonList(notifyAt);
        }

        return IntStream.range(0, repeatInfo.times)
                .mapToObj(i -> {
                            long increment = (long) repeatInfo.interval.intervalValue * i;
                            switch (repeatInfo.interval.timeUnit) {
                                case DAY:
                                    return notifyAt.plusDays(increment);
                                case WEEK:
                                    return notifyAt.plusWeeks(increment);
                                case MONTH:
                                    return notifyAt.plusMonths(increment);
                                case YEAR:
                                    return notifyAt.plusYears(increment);
                                default:
                                    throw new CalendarException(ErrorCode.BAD_REQUEST);
                            }
                        }
                )
                .collect(toList());
    }

    @Data
    public static class RepeatInfo {
        private final Interval interval; // Interval : 기간 정보 예) 3일에 한번
        private final int times;    //times : 몇번 반복할 것인지

        @ConstructorProperties({"interval","times"})
        public RepeatInfo(Interval interval, int times) {
            this.interval = interval;
            this.times = times;
        }
    }

    @Data
    public static class Interval {
        private final int intervalValue; //숫자에 대한 정보 3,4,5
        private final TimeUnit timeUnit; //시간에 대한 개념 년, 월, 일, 주

        @ConstructorProperties({"intervalValue","timeUnit"})
        public Interval(int intervalValue, TimeUnit timeUnit) {
            this.intervalValue = intervalValue;
            this.timeUnit = timeUnit;
        }
    }
}
