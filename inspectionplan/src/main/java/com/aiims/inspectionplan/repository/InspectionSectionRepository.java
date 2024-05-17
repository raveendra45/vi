package com.aiims.inspectionplan.repository;

import com.aiims.inspectionplan.entity.InspectionSection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InspectionSectionRepository extends JpaRepository<InspectionSection, Integer> {
    // Custom query to get sections by parent id
    List<InspectionSection> findByParentSectionId(InspectionSection parentSection);
}
