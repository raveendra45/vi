package com.aiims.inspectionplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aiims.inspectionplan.entity.UsageMode;
@Repository
public interface UsageModeRepository extends JpaRepository<UsageMode, Integer>{

}