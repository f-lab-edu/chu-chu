package com.example.chuchu.board.entity;

import com.example.chuchu.board.dto.BoardResponseDTO;
import com.example.chuchu.common.global.BaseTimeEntity;
import com.example.chuchu.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "board")
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "hash_tag")
    private String hashTag;

    @Column(name = "content", nullable = false)
    private String content;

    @ColumnDefault("0")
    @Column(name = "like_count", nullable = false)
    private Integer likeCount;

    @ColumnDefault("0")
    @Column(name = "view_count",nullable = false)
    private Integer viewCount;

    @ColumnDefault("0")
    @Column(name = "comment_count",nullable = false)
    private Integer commentCount;

    @Column(name = "is_secret",nullable = false)
    private Boolean isSecret;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member writer;

    @Enumerated(EnumType.STRING)
    @Column(name = "board_type")
    private BoardType boardType;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    public Board(Long id, String title, String hashTag, String content, Integer likeCount, Integer viewCount, Integer commentCount,
                 Boolean isSecret, Member writer, BoardType boardType, Category category) {
        this.id = id;
        this.title = title;
        this.hashTag = hashTag;
        this.content = content;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.isSecret = isSecret;
        this.writer = writer;
        this.boardType = boardType;
        this.category = category;
    }

    public Board updateBoard(BoardResponseDTO boardResponseDto) {
        this.title = boardResponseDto.getTitle();
        this.content = boardResponseDto.getContent();
        this.likeCount = boardResponseDto.getLikeCount();
        this.isSecret = boardResponseDto.getIsSecret();
        return this;
    }
}
