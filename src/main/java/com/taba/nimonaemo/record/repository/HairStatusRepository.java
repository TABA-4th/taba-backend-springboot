package com.taba.nimonaemo.record.repository;

import com.taba.nimonaemo.record.model.entity.HairStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HairStatusRepository extends JpaRepository<HairStatus, Long> {
    @Query("select h from HairStatus h " +
            "where h.member.id = :memberId and " +
            "h.member.status = 'ACTIVE'")
    Optional<HairStatus> findByMemberId(Long memberId);
}
