package com.aiims.inspectionplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aiims.inspectionplan.entity.InspectionStages;

@Repository
public interface InspectionStagesRepository extends JpaRepository<InspectionStages, Integer> {
    // Additional query methods can be defined here if needed
}
