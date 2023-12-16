package com.taba.nimonaemo.member.repository;

import com.taba.nimonaemo.member.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> , JpaSpecificationExecutor<Member> {

    @Query("select m from Member m where m.status = 'ACTIVE' and m.id = :id")
    Optional<Member> findById(@Param("id") Long id);

    @Query("select m from Member m where m.status = 'ACTIVE' and m.phone = :phone")
    Optional<Member> findByPhone(@Param("phone") String phone);

    @Query("select m from Member m where m.status = 'ACTIVE' and m.nickname = :nickname")
    Optional<Member> findByNickname(@Param("nickname") String nickname);

    @Query("select m from Member m where m.status = 'ACTIVE' and m.name = :name")
    Optional<Member> findByName(String name);
}
