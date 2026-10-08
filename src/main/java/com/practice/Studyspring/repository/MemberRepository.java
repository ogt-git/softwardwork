package com.practice.Studyspring.repository;

import com.practice.Studyspring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save (Member member);
    Optional<Member> findById (long id);
    Optional<Member> findByName (String name);
    List<Member> findAll ();
    /*void clearStore();*/
}
