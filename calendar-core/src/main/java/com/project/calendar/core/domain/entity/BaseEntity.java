package com.project.calendar.core.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass //상속을 한 class가 여기서 생성한 것들을 사용할 수 있다 정도로 이해
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate    //AuditingListener에서 현재 시간을 주입하여줌
    private LocalDateTime createdAt;

    @LastModifiedDate    //commit 직전에 현재 시간을 붙여줌
    private LocalDateTime updatedAt;

    public BaseEntity(Long id) {
        this.id = id;
    }
}
