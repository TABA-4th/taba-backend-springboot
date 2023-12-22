package com.taba.nimonaemo.diagnosis.repository;

import com.taba.nimonaemo.diagnosis.model.entity.DiagnosisResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DiagnosisResultRepository extends JpaRepository<DiagnosisResult, Long> {
    @Query("select d from DiagnosisResult d " +
            "where d.member.id = :memberId and " +
            "d.member.status = 'ACTIVE' and " +
            "(d.diagnosisDate between TO_DATE(:curDate, 'YYYY/MM') and TO_DATE(:nextDate, 'YYYY/MM'))")
    List<DiagnosisResult> findByDate(Long memberId, String curDate, String nextDate);

    @Query("select d from DiagnosisResult d " +
            "where d.member.id = :memberId and " +
            "d.member.status = 'ACTIVE' and " +
            "d.diagnosisDate = :date ")
    Optional<DiagnosisResult> findByDateForDetail(Long memberId, LocalDateTime date);

    @Modifying
    @Query(value = "delete from diagnosis_result " +
            "where member_id = (select member_id from member where member_id = :memberId and status = 'ACTIVE') and " +
            "diagnosis_date LIKE CONCAT(:targetDate, '%')", nativeQuery = true)
    void deleteByDate(Long memberId, LocalDateTime targetDate);

    @Query("select COUNT(*) from DiagnosisResult d " +
            "where d.member.id = :memberId and " +
            "d.member.status = 'ACTIVE'")
    Long findAllWithId(Long memberId);
}
