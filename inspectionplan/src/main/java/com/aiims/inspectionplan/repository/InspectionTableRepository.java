package com.aiims.inspectionplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aiims.inspectionplan.entity.InspectionTable;
@Repository

public interface InspectionTableRepository extends JpaRepository<InspectionTable, Integer> {
    // JpaRepository provides all basic CRUD operations.
}
