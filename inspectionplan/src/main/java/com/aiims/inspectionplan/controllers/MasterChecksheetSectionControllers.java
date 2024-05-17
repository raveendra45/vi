package com.aiims.inspectionplan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.aiims.inspectionplan.services.MasterChecksheetSectionServices;
import com.aiims.inspectionplan.Dto.MasterChecksheetSectionDto;

import java.util.List;

@RestController
@RequestMapping("/api/checksheet-sections")
public class MasterChecksheetSectionControllers {
    @Autowired
    private MasterChecksheetSectionServices service;

    @GetMapping("/{id}")
    public ResponseEntity<MasterChecksheetSectionDto> getById(@PathVariable int id) {
        MasterChecksheetSectionDto section = service.findById(id);
        return ResponseEntity.ok(section);
    }

    @GetMapping
    public ResponseEntity<List<MasterChecksheetSectionDto>> getAll() {
        List<MasterChecksheetSectionDto> sections = service.findAll();
        return ResponseEntity.ok(sections);
    }

    @PostMapping
    public ResponseEntity<MasterChecksheetSectionDto> create(@RequestBody MasterChecksheetSectionDto dto) {
        MasterChecksheetSectionDto created = service.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MasterChecksheetSectionDto> update(@PathVariable int id, @RequestBody MasterChecksheetSectionDto dto) {
        MasterChecksheetSectionDto updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
