package com.vone.vone.data.repository;

import com.vone.vone.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(String username);

    boolean existsByMemberId(String username);
}