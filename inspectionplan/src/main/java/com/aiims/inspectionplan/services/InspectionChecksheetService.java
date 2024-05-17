package com.aiims.inspectionplan.services;

import com.aiims.inspectionplan.entity.InspectionChecksheet;
import com.aiims.inspectionplan.Dto.InspectionChecksheetDto;
import com.aiims.inspectionplan.repository.InspectionChecksheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InspectionChecksheetService {

    @Autowired
    private InspectionChecksheetRepository checksheetRepository;

    public InspectionChecksheet createChecksheet(InspectionChecksheetDto dto) {
        InspectionChecksheet checksheet = new InspectionChecksheet();
        checksheet.setChecksheetName(dto.getChecksheetName());
        // Set other fields based on the dto
        return checksheetRepository.save(checksheet);
    }

    public InspectionChecksheet updateChecksheet(int id, InspectionChecksheetDto dto) {
        Optional<InspectionChecksheet> existingChecksheet = checksheetRepository.findById(id);
        if (existingChecksheet.isPresent()) {
            InspectionChecksheet checksheet = existingChecksheet.get();
            checksheet.setChecksheetName(dto.getChecksheetName());
            // Set other fields based on the dto
            return checksheetRepository.save(checksheet);
        } else {
            throw new IllegalArgumentException("Checksheet not found with ID: " + id);
        }
    }

    public void deleteChecksheet(int id) {
        checksheetRepository.deleteById(id);
    }

    public Optional<InspectionChecksheet> getChecksheetById(int id) {
        return checksheetRepository.findById(id);
    }

    public List<InspectionChecksheet> getAllChecksheets() {
        return checksheetRepository.findAll();
    }
}
