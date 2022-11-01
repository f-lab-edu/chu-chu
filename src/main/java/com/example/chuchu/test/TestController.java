package com.example.chuchu.test;

import com.example.chuchu.board.dto.BoardResponseDTO;
import com.example.chuchu.board.mapper.BoardResponseMapper;
import com.example.chuchu.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final BoardService boardService;
    private final BoardResponseMapper boardResponseMapper;

    @GetMapping
    public String test() {
        return "Hello, this application is chuchu test jenkins0.0.1";
    }

    @GetMapping("/board/{title}")
    public BoardResponseDTO getBoard(@PathVariable(value = "title") String title) {
        return boardResponseMapper.toDto(boardService.getBoardByTitle(title));
    }
}
