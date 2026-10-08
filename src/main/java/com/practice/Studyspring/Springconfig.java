package com.practice.Studyspring;

import com.practice.Studyspring.repository.*;
import com.practice.Studyspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Springconfig {

    private final MemberRepository memberRepository;

    @Autowired
    public Springconfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;

    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

  /*  @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }*/

   /* @Bean
    public MemberRepository memberRepository() {
        return new
    }*/
}
