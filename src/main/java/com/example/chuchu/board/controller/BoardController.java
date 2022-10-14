package com.example.chuchu.board.controller;

import com.example.chuchu.board.dto.BoardDTO;
import com.example.chuchu.board.mapper.BoardMapper;
import com.example.chuchu.board.service.BoardService;
import com.example.chuchu.common.global.PageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.chuchu.common.global.HttpResponseEntity.ResponseResult;
import static com.example.chuchu.common.global.HttpResponseEntity.success;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final BoardMapper boardMapper;

    @GetMapping("/{id}")
    public ResponseResult<BoardDTO> getOne(@PathVariable Long id) {
        return success(boardMapper.toDto(boardService.findById(id)));
    }

    @GetMapping("/list")
    public ResponseResult<?> getList(@RequestParam(value = "query", required = false) String query,
                                     @RequestParam(value = "page", required = false) Integer page,
                                     @RequestParam(value = "sort", required = false) String sort) {

        PageRequest pageRequest = new PageRequest(
                page == null ? 1 : page, 20, Sort.Direction.DESC, sort == null ? "id" : sort);
        PageImpl<BoardDTO> boardList = boardService.getBoardList(query, pageRequest.of());
        return success(boardList);
    }

    @PostMapping
    public ResponseResult<BoardDTO> insert(@RequestBody @Valid BoardDTO boardDto) {
        return success(boardMapper.toDto(boardService.insert(boardDto)));
    }

    @PutMapping("/{id}")
    public ResponseResult<BoardDTO> update(@RequestBody @Valid BoardDTO boardDto, @PathVariable long id) {
        return success(boardMapper.toDto(boardService.update(boardDto, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseResult<BoardDTO> delete(@PathVariable long id) {
        return success(boardMapper.toDto(boardService.delete(id)));
    }
}
