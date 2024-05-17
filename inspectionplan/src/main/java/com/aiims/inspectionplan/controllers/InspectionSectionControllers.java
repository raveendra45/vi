package com.aiims.inspectionplan.controllers;

import com.aiims.inspectionplan.Dto.InspectionSectionDto;
import com.aiims.inspectionplan.services.InspectionSectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inspection-sections")
public class InspectionSectionControllers {

    private final InspectionSectionService inspectionSectionService;

    public InspectionSectionControllers(InspectionSectionService inspectionSectionService) {
        this.inspectionSectionService = inspectionSectionService;
    }

    @GetMapping
    public List<InspectionSectionDto> getAll() {
        return inspectionSectionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InspectionSectionDto> getById(@PathVariable int id) {
        return inspectionSectionService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("save")
    public ResponseEntity<InspectionSectionDto> create(@RequestBody InspectionSectionDto dto) {
        InspectionSectionDto createdDto = inspectionSectionService.create(dto);
        return ResponseEntity.status(201).body(createdDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InspectionSectionDto> update(@PathVariable int id, @RequestBody InspectionSectionDto dto) {
        return inspectionSectionService.update(id, dto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        boolean deleted = inspectionSectionService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
