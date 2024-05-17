package com.aiims.inspectionplan.controllers;

import com.aiims.inspectionplan.Dto.InspectionChecksheetDto;
import com.aiims.inspectionplan.entity.InspectionChecksheet;
import com.aiims.inspectionplan.services.InspectionChecksheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/checksheets")
public class InspectionChecksheetControllers {

    @Autowired
    private InspectionChecksheetService checksheetService;

    @PostMapping("/")
    public ResponseEntity<InspectionChecksheet> createChecksheet(@RequestBody InspectionChecksheetDto dto) {
        InspectionChecksheet checksheet = checksheetService.createChecksheet(dto);
        return ResponseEntity.ok(checksheet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InspectionChecksheet> updateChecksheet(@PathVariable("id") int id, @RequestBody InspectionChecksheetDto dto) {
        InspectionChecksheet updatedChecksheet = checksheetService.updateChecksheet(id, dto);
        return ResponseEntity.ok(updatedChecksheet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChecksheet(@PathVariable("id") int id) {
        checksheetService.deleteChecksheet(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InspectionChecksheet> getChecksheetById(@PathVariable("id") int id) {
        Optional<InspectionChecksheet> checksheet = checksheetService.getChecksheetById(id);
        if (checksheet.isPresent()) {
            return ResponseEntity.ok(checksheet.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<InspectionChecksheet>> getAllChecksheets() {
        List<InspectionChecksheet> checksheets = checksheetService.getAllChecksheets();
        return ResponseEntity.ok(checksheets);
    }
}
