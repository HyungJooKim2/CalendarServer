package com.project.calendar.api;

import com.project.calendar.core.SimpleEntity;
import com.project.calendar.core.SimpleEntityRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//패키지 구조 하위에서 entity repository 를 가져올 수있다.
@EntityScan("com.project.calendar.core")
@EnableJpaRepositories("com.project.calendar.core")
@RestController
@SpringBootApplication(scanBasePackages = "com.project.calendar")
@AllArgsConstructor
@EnableJpaAuditing //Auditing과 관련된 bean들이 이용가능됨
public class ApiApplication {

    private final SimpleEntityRepository repository;

    @GetMapping
    public List<SimpleEntity> findAlll(){
        return repository.findAll();
    }

    @PostMapping("/save")
    public SimpleEntity saveOne(){
        final SimpleEntity e = new SimpleEntity();
        e.setName("hello");
        return repository.save(e);
    }


    public static void main(String[] args){
        SpringApplication.run(ApiApplication.class, args);
    }
}
