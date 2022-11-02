package com.example.chuchu.comment.controller;

import com.example.chuchu.comment.dto.CommentRequestDTO;
import com.example.chuchu.comment.entity.Comment;
import com.example.chuchu.comment.service.CommentService;
import com.example.chuchu.common.global.HttpResponseEntity.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.example.chuchu.common.global.HttpResponseEntity.success;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/chuchu/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{boardId}")
    public ResponseResult<Comment> insert(@PathVariable Long boardId,
                                          @RequestBody CommentRequestDTO commentRequestDTO) {

        // TODO 뭘 return 하는게 좋을지 고민해보자
        commentService.insert(boardId, commentRequestDTO);
        return success();
    }

    @PutMapping("/{commentId}")
    public ResponseResult<Comment> update(@PathVariable Long commentId, @RequestBody CommentRequestDTO commentRequestDTO) {
        commentService.update(commentId, commentRequestDTO);
        // TODO 뭘 return 하는게 좋을지 고민해보자
        return success();
    }

    @DeleteMapping("/{commentId}")
    //TODO return Type 명시하기
    public ResponseResult<Comment> delete(@PathVariable Long commentId) {
        commentService.delete(commentId);
        // TODO 뭘 return 하는게 좋을지 고민해보자
        return success();
    }

}
