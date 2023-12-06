package com.taba.nimonaemo.record.repository;

import com.taba.nimonaemo.record.model.entity.HairStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HairStatusRepository extends JpaRepository<HairStatus, Long> {
}
