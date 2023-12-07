package com.taba.nimonaemo.record.repository;

import com.taba.nimonaemo.record.model.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    @Query("select d from Diary d " +
            "where d.member.id = :memberId and " +
            "(d.diaryDate between TO_DATE(:curDate, 'YYYY/MM') and TO_DATE(:nextDate, 'YYYY/MM'))")
    List<Diary> findByDate(Long memberId, String curDate, String nextDate);

    @Transactional
    @Modifying
    @Query("delete from Diary d " +
            "where d.member.id = :memberId and " +
            "d.id = :diaryId ")
    void deleteByDate(Long memberId, Long diaryId);
}