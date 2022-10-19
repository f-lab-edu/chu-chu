package com.example.chuchu.comment.controller;

import com.example.chuchu.board.dto.BoardRequestDTO;
import com.example.chuchu.board.dto.BoardResponseDTO;
import com.example.chuchu.comment.dto.CommentRequestDTO;
import com.example.chuchu.comment.service.CommentService;
import com.example.chuchu.common.global.HttpResponseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.chuchu.common.global.HttpResponseEntity.success;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/chuchu/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{boardId}")
    public HttpResponseEntity.ResponseResult<BoardResponseDTO> insert(@PathVariable Long boardId,
                                                                      @RequestBody CommentRequestDTO commentRequestDTO) {

        commentService.insert(boardId, commentRequestDTO);

        return null;
    }

}
