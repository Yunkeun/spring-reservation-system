package com.yoonveloping.reservationsystem.repository;

import com.yoonveloping.reservationsystem.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
