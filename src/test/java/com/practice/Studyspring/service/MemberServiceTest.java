package com.practice.Studyspring.service;

import com.practice.Studyspring.repository.MemoryMemberRepository;
import com.practice.Studyspring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void join() {
        Member member = new Member();
        member.setName("jointest");

        Long saveid = memberService.join(member);

        Member findMember = memberRepository.findById(saveid).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    void checkduplicateMember() {
        Member member1 = new Member();
        member1.setName("hola");

        Member member2 = new Member();
        member2.setName("hola");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
    
    }

    @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}