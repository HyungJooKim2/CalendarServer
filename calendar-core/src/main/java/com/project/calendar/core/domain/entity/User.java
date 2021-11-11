package com.project.calendar.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User extends BaseEntity{

    private String name;
    private String email;
    private String password;
    private LocalDate birthday;

    public User(String name, String email, String pw, LocalDateTime now) {
    }
}
