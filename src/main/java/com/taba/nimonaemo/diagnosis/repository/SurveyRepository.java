package com.taba.nimonaemo.diagnosis.repository;

import com.taba.nimonaemo.diagnosis.model.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    @Query("select s from Survey s " +
            "where s.member.id = :memberId and " +
            "s.surveyDate = :targetDate ")
    Optional<Survey> findByDate(Long memberId, LocalDateTime targetDate);
}
