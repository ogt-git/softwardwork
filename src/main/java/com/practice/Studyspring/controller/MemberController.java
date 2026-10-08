package com.practice.Studyspring.controller;

import com.practice.Studyspring.domain.Member;
import com.practice.Studyspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {this.memberService = memberService;}


    @GetMapping("/members/new")
    public String newMember(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String createMember(memberForm memberForm){
        Member member = new Member();
        member.setName(memberForm.getName());
        member.setPassword(memberForm.getPassword());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String lists(Model model){
        List<Member> member = memberService.findMember();
        model.addAttribute("members", member);

        return "members/memberList";
    }
}
