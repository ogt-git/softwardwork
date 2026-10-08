package com.practice.Studyspring.service;

import com.practice.Studyspring.repository.MemberRepository;
import com.practice.Studyspring.domain.Member;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
class MemberServiceIntTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

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