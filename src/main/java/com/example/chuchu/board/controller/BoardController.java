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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.List;

import static com.example.chuchu.common.global.HttpResponseEntity.ResponseResult;
import static com.example.chuchu.common.global.HttpResponseEntity.success;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/chuchu")
public class BoardController {

    private final BoardService boardService;
    private final BoardResponseMapper boardResponseMapper;

    @GetMapping("/best/{boardType}")
    public ResponseResult<?> getBestList(@PathVariable(value = "boardType") String boardType) {
        BoardType enumBoardType = BoardType.valueOf(boardType.toUpperCase());
        List<BoardResponseDTO> bestList = boardService.getBestList(enumBoardType);
        return success(bestList);
    }

    @GetMapping("/{boardType}")
    public ResponseResult<PageImpl<BoardResponseDTO>> getList(@PathVariable(value = "boardType") String boardType,
                                                              @RequestParam(value = "query", required = false) String query,
                                                              @RequestParam(value = "page", required = false) Integer page,
                                                              @RequestParam(value = "sort", required = false) String sort) {

        //TODO ENUM 설정 해볼 것
        BoardType enumBoardType = BoardType.valueOf(boardType.toUpperCase());

        PageRequest pageRequest = new PageRequest(
                page == null ? 1 : page, 20, Sort.Direction.DESC, sort == null ? "id" : sort);
        PageImpl<BoardResponseDTO> boardList = boardService.getBoardList(query, enumBoardType, pageRequest.of());
        return success(boardList);
    }

    @GetMapping("/detail/{id}")
    public ResponseResult<BoardResponseDTO> getOne(@PathVariable(value = "id") Long id, HttpServletRequest req, HttpServletResponse res) {
        viewCountUp(id, req, res);
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

    private void viewCountUp(Long id, HttpServletRequest req, HttpServletResponse res) {

        Cookie oldCookie = null;

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("boardView")) {
                    oldCookie = cookie;
                }
            }
        }

        if (oldCookie != null) {
            if (!oldCookie.getValue().contains("[" + id.toString() + "]")) {
                boardService.viewCountUp(id);
                oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);
                res.addCookie(oldCookie);
            }
        } else {
            boardService.viewCountUp(id);
            Cookie newCookie = new Cookie("boardView","[" + id + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            res.addCookie(newCookie);
        }
    }

}

