package com.example.chuchu.heart.service;

import com.example.chuchu.board.entity.Board;
import com.example.chuchu.board.repository.BoardRepository;
import com.example.chuchu.common.errors.exception.NotFoundException;
import com.example.chuchu.heart.dto.HeartRequestDTO;
import com.example.chuchu.heart.entity.Heart;
import com.example.chuchu.heart.repository.HeartRepository;
import com.example.chuchu.member.entity.Member;
import com.example.chuchu.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void insert(HeartRequestDTO heartRequestDTO) throws Exception {

        Member member = memberRepository.findById(heartRequestDTO.getMemberId())
                .orElseThrow(() -> new NotFoundException("Could not found member id : " + heartRequestDTO.getMemberId()));

        Board board = boardRepository.findById(heartRequestDTO.getBoardId())
                .orElseThrow(() -> new NotFoundException("Could not found board id : " + heartRequestDTO.getBoardId()));

        // 이미 좋아요되어있으면 에러 반환
        if (heartRepository.findByMemberAndBoard(member, board).isPresent()){
            //TODO 409에러로 변경
            throw new Exception();
        }

        Heart heart = Heart.builder()
                .board(board)
                .member(member)
                .build();

        heartRepository.save(heart);
        boardRepository.updateCount(board,true);
    }

    @Transactional
    public void delete(HeartRequestDTO heartRequestDTO) {

        Member member = memberRepository.findById(heartRequestDTO.getMemberId())
                .orElseThrow(() -> new NotFoundException("Could not found member id : " + heartRequestDTO.getMemberId()));

        Board board = boardRepository.findById(heartRequestDTO.getBoardId())
                .orElseThrow(() -> new NotFoundException("Could not found board id : " + heartRequestDTO.getBoardId()));

        Heart heart = heartRepository.findByMemberAndBoard(member, board)
                .orElseThrow(() -> new NotFoundException("Could not found heart id"));

        heartRepository.delete(heart);
        boardRepository.updateCount(board,false);
    }
}
