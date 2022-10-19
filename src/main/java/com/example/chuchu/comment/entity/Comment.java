package com.example.chuchu.comment.entity;

import com.example.chuchu.board.entity.Board;
import com.example.chuchu.common.global.BaseTimeEntity;
import com.example.chuchu.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DynamicInsert
@Table(name = "comment")
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false, length = 1000)
    private String content;

    @ColumnDefault("FALSE")
    @Column(nullable = false)
    private Boolean isDeleted;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member writer;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Comment> children = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public Comment(String content) {
        this.content = content;
    }

    public void updateWriter(Member member) {
        this.writer = member;
    }

    public void updateBoard(Board board) {
        this.board = board;
    }

    public void updateParent(Comment comment) {
        this.parent = comment;
    }

    public Comment changeIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }
}
