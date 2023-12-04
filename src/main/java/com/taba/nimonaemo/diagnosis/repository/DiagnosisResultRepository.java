package com.taba.nimonaemo.diagnosis.repository;

import com.taba.nimonaemo.diagnosis.model.entity.DiagnosisResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface DiagnosisResultRepository extends JpaRepository<DiagnosisResult, Long> {
    @Query("select d from DiagnosisResult d " +
            "where d.member.id = :memberId and " +
            "(d.diagnosisDate between TO_DATE(:curDate, 'YYYY/MM') and TO_DATE(:nextDate, 'YYYY/MM'))")
    List<DiagnosisResult> findByDate(Long memberId, String curDate, String nextDate);

    @Transactional
    @Modifying
    @Query("delete from DiagnosisResult d " +
            "where d.member.id = :memberId and " +
                    "d.diagnosisDate LIKE CONCAT(:targetDate, '%')")
    void deleteByDate(Long memberId, LocalDateTime targetDate);

    @Query("select COUNT(*) from DiagnosisResult d " +
            "where d.member.id = (select m.id from Member m " +
                                "where m.nickname = :nickname) ")
    Long findAllWithNickname(String nickname);
}
