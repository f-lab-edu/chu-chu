/*
package com.example.chuchu.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/member")
public class MemberController {


    private final MemberService memberService;

    private final MemberMapper memberMapper;

    @GetMapping("/list")
    public ResponseResult<List<MemberDTO>> getList() {
        return success(memberMapper.toDtoList(memberService.findAll()));
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {


        return "redirect:/member";
    }

    @PostMapping("/join")
    public ResponseResult<MemberDTO> join(@RequestBody @Valid MemberDTO memberDTO) {
        return success(memberMapper.toDto(memberService.join(memberDTO)));
    }


}
*/
