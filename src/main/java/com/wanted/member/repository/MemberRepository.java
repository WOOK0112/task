package com.wanted.member.repository;

import com.wanted.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(long id);

    Optional<Member> findByEmail(String email);
}
