/*
package com.practice.Studyspring.repository;

import com.practice.Studyspring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MemoryMemberRepositoryTest {
    MemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        //repository.clearStore();
    }

    @Test
    public void saveTest() {
        Member member1 = new Member();
        member1.setName("user1");
        Member member2 = new Member();
        member1.setName("user2");
        repository.save(member1);
        repository.save(member2);
        Optional<Member> result = repository.findById(member1.getId());
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findByNameTest() {
        Member member1 = new Member();
        member1.setName("user1");

        Member member2 = new Member();
        member1.setName("user2");

        repository.save(member1);
        repository.save(member2);

        Member result = repository.findByName(member1.getName()).get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAllTest() {
        Member member1 = new Member();
        member1.setName("user1");
        repository.save(member1);
        Member member2 = new Member();
        member1.setName("user2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        for(int i=0;i<result.size();i++){
            System.out.println(result.get(i).getId());
        }

    }
}*/
