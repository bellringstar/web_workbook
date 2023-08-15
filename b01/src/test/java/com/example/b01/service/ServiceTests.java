package com.example.b01.service;

import com.example.b01.dto.BoardDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister(){
        log.info(boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                .title("sample title..")
                .content("Sample Content..")
                .writer("user00")
                .build();
        Long bno = boardService.register(boardDTO);

        log.info("bno : " + bno);
    }
}
