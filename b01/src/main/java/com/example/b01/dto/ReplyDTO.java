package com.example.b01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {

    private Long rno;
    @NotNull
    private Long bno;
    @NotEmpty
    private String replyText;
    @NotEmpty
    private String replyer;
    private LocalDateTime regDate, modDate;
}