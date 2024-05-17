package com.aiims.inspectionplan.repository;

import com.aiims.inspectionplan.entity.InspectionChecksheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspectionChecksheetRepository extends JpaRepository<InspectionChecksheet, Integer> {
}
