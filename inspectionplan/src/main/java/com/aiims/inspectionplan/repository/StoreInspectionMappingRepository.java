package com.aiims.inspectionplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aiims.inspectionplan.entity.StoreInspectionMapping;

@Repository
public interface StoreInspectionMappingRepository extends JpaRepository<StoreInspectionMapping, Integer> {
    // Additional query methods can be defined here, if necessary
}