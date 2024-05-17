package com.aiims.inspectionplan.controllers;

import com.aiims.inspectionplan.Dto.InspectionStagesDto;
import com.aiims.inspectionplan.services.InspectionStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inspectionStages")
public class InspectionStagesControllers {

    @Autowired
    private InspectionStageService inspectionStagesService;

    @GetMapping
    public ResponseEntity<List<InspectionStagesDto>> getAllInspectionStages() {
        List<InspectionStagesDto> inspectionStages = inspectionStagesService.getAllInspectionStages();
        return ResponseEntity.ok(inspectionStages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InspectionStagesDto> getInspectionStageById(@PathVariable("id") int id) {
        Optional<InspectionStagesDto> inspectionStage = inspectionStagesService.getInspectionStageById(id);
        return inspectionStage.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("save")
    public ResponseEntity<InspectionStagesDto> createInspectionStage(@RequestBody InspectionStagesDto inspectionStagesDto) {
        InspectionStagesDto savedInspectionStage = inspectionStagesService.saveInspectionStage(inspectionStagesDto);
        return new ResponseEntity<>(savedInspectionStage, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInspectionStage(@PathVariable("id") int id) {
        inspectionStagesService.deleteInspectionStage(id);
        return ResponseEntity.noContent().build();
    }
}
