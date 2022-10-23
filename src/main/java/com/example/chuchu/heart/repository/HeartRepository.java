package com.example.chuchu.heart.repository;

import com.example.chuchu.board.entity.Board;
import com.example.chuchu.heart.entity.Heart;
import com.example.chuchu.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByMemberAndBoard(Member member, Board board);
}
