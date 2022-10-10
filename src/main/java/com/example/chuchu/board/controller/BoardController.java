package com.example.chuchu.board.controller;

import com.example.chuchu.board.dto.BoardDto;
import com.example.chuchu.board.mapper.BoardMapper;
import com.example.chuchu.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.chuchu.common.utils.ApiUtils.ApiResult;
import static com.example.chuchu.common.utils.ApiUtils.success;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final BoardMapper boardMapper;

    @GetMapping("/{id}")
    public ApiResult<BoardDto> getOne(@PathVariable Long id) {
        return success(boardMapper.toDto(boardService.findById(id)));
    }

    @GetMapping("/list")
    public ApiResult<List<BoardDto>> getList() {
        return success(boardMapper.toDtoList(boardService.findAll()));
    }

    @PostMapping
    public ApiResult<BoardDto> insert(@RequestBody @Valid BoardDto boardDto) {
        return success(boardMapper.toDto(boardService.insert(boardDto)));
    }

    @PutMapping("/{id}")
    public ApiResult<BoardDto> update(@RequestBody @Valid BoardDto boardDto, @PathVariable long id) {
        return success(boardMapper.toDto(boardService.update(boardDto, id)));
    }

    @DeleteMapping("/{id}")
    public ApiResult<BoardDto> delete(@PathVariable long id) {
        return success(boardMapper.toDto(boardService.delete(id)));
    }
}
