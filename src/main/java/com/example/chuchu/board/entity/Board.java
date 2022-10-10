package com.example.chuchu.board.entity;

import com.example.chuchu.board.dto.BoardDto;
import com.example.chuchu.common.global.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "board")
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private long good;

    private boolean secret;

    @Builder
    public Board(String title, String content, long good, boolean secret) {
        this.title = title;
        this.content = content;
        this.good = good;
        this.secret = secret;
    }

    public Board updateBoard(BoardDto boardDto) {
        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
        this.good = boardDto.getGood();
        this.secret = boardDto.isSecret();
        return this;
    }
}
