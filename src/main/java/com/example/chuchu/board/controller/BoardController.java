package com.example.chuchu.board.controller;

import com.example.chuchu.board.dto.BoardDTO;
import com.example.chuchu.board.entity.BoardType;
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
@RequestMapping("/chuchu")
public class BoardController {

    private final BoardService boardService;
    private final BoardMapper boardMapper;

    @GetMapping("/{boardType}")
    public ResponseResult<?> getList(@PathVariable(value = "boardType") String boardType,
                                     @RequestParam(value = "query", required = false) String query,
                                     @RequestParam(value = "page", required = false) Integer page,
                                     @RequestParam(value = "sort", required = false) String sort) {

        //TODO ENUM 설정 해볼 것
        BoardType boardType1 = BoardType.valueOf(boardType.toUpperCase());

        PageRequest pageRequest = new PageRequest(
                page == null ? 1 : page, 20, Sort.Direction.DESC, sort == null ? "id" : sort);
        PageImpl<BoardDTO> boardList = boardService.getBoardList(query, boardType1, pageRequest.of());
        return success(boardList);
    }

    @GetMapping("/{boardType}/{id}")
    public ResponseResult<BoardDTO> getOne(@PathVariable(value = "boardType") String boardType,
                                           @PathVariable(value = "id") Long id) {
        return success(boardService.getBoardWithTag(id));
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
