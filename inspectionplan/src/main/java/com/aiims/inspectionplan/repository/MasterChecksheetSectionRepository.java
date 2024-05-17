package com.aiims.inspectionplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aiims.inspectionplan.entity.MasterChecksheetSection;

@Repository
public interface MasterChecksheetSectionRepository extends JpaRepository<MasterChecksheetSection, Integer> {
    // Additional query methods can be defined here if needed
}
