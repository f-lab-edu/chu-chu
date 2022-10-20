package com.example.chuchu.board.controller;

import com.example.chuchu.board.dto.BoardRequestDTO;
import com.example.chuchu.board.dto.BoardResponseDTO;
import com.example.chuchu.board.entity.BoardType;
import com.example.chuchu.board.mapper.BoardResponseMapper;
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
    private final BoardResponseMapper boardResponseMapper;

    @GetMapping("/{boardType}")
    public ResponseResult<PageImpl<BoardResponseDTO>> getList(@PathVariable(value = "boardType") String boardType,
                                                              @RequestParam(value = "query", required = false) String query,
                                                              @RequestParam(value = "page", required = false) Integer page,
                                                              @RequestParam(value = "sort", required = false) String sort) {

        //TODO ENUM 설정 해볼 것
        BoardType boardType1 = BoardType.valueOf(boardType.toUpperCase());

        PageRequest pageRequest = new PageRequest(
                page == null ? 1 : page, 20, Sort.Direction.DESC, sort == null ? "id" : sort);
        PageImpl<BoardResponseDTO> boardList = boardService.getBoardList(query, boardType1, pageRequest.of());
        return success(boardList);
    }


    //TODO boardType이 필요없는데...위랑 중복된다. 고민해보자
    @GetMapping("/{boardType}/{id}")
    public ResponseResult<BoardResponseDTO> getOne(@PathVariable(value = "boardType") String boardType,
                                                   @PathVariable(value = "id") Long id) {
        return success(boardService.getBoardWithTag(id));
    }

    @PostMapping
    public ResponseResult<BoardResponseDTO> insert(@RequestBody @Valid BoardRequestDTO boardRequestDTO) {
        return success(boardResponseMapper.toDto(boardService.insert(boardRequestDTO)));
    }

    @PutMapping("/{id}")
    public ResponseResult<BoardResponseDTO> update(@PathVariable Long id,
                                                   @RequestBody @Valid BoardRequestDTO boardRequestDTO) {
        return success(boardResponseMapper.toDto(boardService.update(boardRequestDTO, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseResult<BoardResponseDTO> delete(@PathVariable Long id) {
        return success(boardResponseMapper.toDto(boardService.delete(id)));
    }
}
