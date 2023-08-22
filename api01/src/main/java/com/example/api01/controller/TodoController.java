package com.example.api01.controller;

import com.example.api01.dto.TodoDTO;
import com.example.api01.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> register(@RequestBody TodoDTO todoDTO) {

        log.info(todoDTO);

        return Map.of("tno", 12L);
    }

    @GetMapping("/{tno}")
    public TodoDTO read(@PathVariable("tno") Long tno) {

        log.info("read tno : " + tno);

        return todoService.read(tno);
    }
}
