package com.wanted.member.controller;

import com.wanted.member.dto.MemberDto;
import com.wanted.member.entity.Member;
import com.wanted.member.mapper.MemberMapper;
import com.wanted.member.service.MemberService;
import com.wanted.note.entity.Note;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
@Validated
@Slf4j
public class MemberController {
    private MemberService memberService;
    private MemberMapper memberMapper;

    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberDto memberDto) {
        Member member = memberService.createMember(memberMapper.memberDtoToMember(memberDto));

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getNote(@Valid @PathVariable("member-id") long memberId) {
        Member member = memberService.findMember(memberId);

        return new ResponseEntity(memberMapper.memberToMemberDto(member), HttpStatus.OK);
    }



}
