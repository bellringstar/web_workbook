package com.example.api01.domain;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class APIUser {

    @Id
    private String mid;
    private String mpw;

    public void changePw(String mpw){
        this.mpw = mpw;
    }
}
