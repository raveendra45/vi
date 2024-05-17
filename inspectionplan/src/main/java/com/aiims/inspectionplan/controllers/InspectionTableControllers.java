package com.aiims.inspectionplan.controllers;

import com.aiims.inspectionplan.Dto.InspectionTableDto;
import com.aiims.inspectionplan.services.InspectionTableServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inspections")  // Base endpoint for all inspection-related endpoints
public class InspectionTableControllers {

    @Autowired
    private InspectionTableServices inspectionService;
    
    
    @PostMapping("/createdbyme")
    public ResponseEntity<InspectionTableDto> createInspectionTable(@RequestBody InspectionTableDto inspectionTableDto) {
        InspectionTableDto createdInspectionTable = inspectionService.createInspectionTable(inspectionTableDto);
        return new ResponseEntity<>(createdInspectionTable, HttpStatus.CREATED);
    }

    // Endpoint to get all inspection tables
    @GetMapping("/all")
    public ResponseEntity<List<InspectionTableDto>> getAllInspectionTables() {
        List<InspectionTableDto> inspectionTableDtos = inspectionService.getAllInspectionTables();
        return new ResponseEntity<>(inspectionTableDtos, HttpStatus.OK);
    }

    // Endpoint to get an inspection table by ID
    @GetMapping("/createdbyme/{id}")
    public ResponseEntity<InspectionTableDto> getInspectionTableById(@PathVariable int id) {
        InspectionTableDto inspectionTableDto = inspectionService.getInspectionTableById(id);
        return ResponseEntity.ok(inspectionTableDto);
    }
    
    
    

    @GetMapping
    public ResponseEntity<List<InspectionTableDto>> getAllInspections() {
        List<InspectionTableDto> inspections = inspectionService.getAllInspections();
        return ResponseEntity.ok(inspections);  // Return a 200 OK response with the list of inspections
    }

    @GetMapping("/{id}")
    public ResponseEntity<InspectionTableDto> getInspectionById(@PathVariable int id) {
        Optional<InspectionTableDto> inspection = inspectionService.getInspectionById(id);
        return inspection.map(ResponseEntity::ok)  // If found, return a 200 OK response
                         .orElse(ResponseEntity.notFound().build());  // If not found, return a 404 response
    }

    @PostMapping
    public ResponseEntity<InspectionTableDto> createInspection(@RequestBody InspectionTableDto inspectionDto) {
        InspectionTableDto createdInspection = inspectionService.createInspection(inspectionDto);
        return ResponseEntity.status(201).body(createdInspection);  // Return a 201 Created response with the new inspection
    }

    @PutMapping("/{id}")
    public ResponseEntity<InspectionTableDto> updateInspection(
            @PathVariable int id,
            @RequestBody InspectionTableDto inspectionDto
    ) {
        Optional<InspectionTableDto> updatedInspection = inspectionService.updateInspection(id, inspectionDto);
        return updatedInspection.map(ResponseEntity::ok)  // If update was successful, return a 200 OK response
                                .orElse(ResponseEntity.notFound().build());  // If the inspection doesn't exist, return 404
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInspection(@PathVariable int id) {
        boolean deleted = inspectionService.deleteInspection(id);  // Return true if deletion was successful
        if (deleted) {
            return ResponseEntity.noContent().build();  // If deleted, return a 204 No Content response
        } else {
            return ResponseEntity.notFound().build();  // If not found, return a 404 response
        }
    }
}
