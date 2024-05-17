package com.aiims.inspectionplan.controllers;

import com.aiims.inspectionplan.Dto.StoreInspectionMappingDto;
import com.aiims.inspectionplan.services.StoreInspectionMappingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store-inspection-mappings")
public class StoreInspectionMappingControllers {

    private final StoreInspectionMappingService storeInspectionMappingService;

    public StoreInspectionMappingControllers(StoreInspectionMappingService storeInspectionMappingService) {
        this.storeInspectionMappingService = storeInspectionMappingService;
    }

    @GetMapping
    public List<StoreInspectionMappingDto> getAllMappings() {
        return storeInspectionMappingService.getAllMappings();
    }

    @GetMapping("/{id}")
    public StoreInspectionMappingDto getMappingById(@PathVariable int id) {
        return storeInspectionMappingService.getMappingById(id);
    }

    @PostMapping("create")
    public ResponseEntity<StoreInspectionMappingDto> createMapping(@RequestBody StoreInspectionMappingDto dto) {
        StoreInspectionMappingDto createdDto = storeInspectionMappingService.createMapping(dto);
        return ResponseEntity.ok(createdDto); // Or use `ResponseEntity.created` with a proper location URI
    }
    @PostMapping("save")
    public ResponseEntity<?> saveStoreInspectionMapping(@RequestBody StoreInspectionMappingDto dto) {
        // Logic to save store mapping data
        StoreInspectionMappingDto savedMapping = storeInspectionMappingService.createMapping(dto);
        return ResponseEntity.ok(savedMapping);
    }

    @PutMapping("/{id}")
    public StoreInspectionMappingDto updateMapping(@PathVariable int id, @RequestBody StoreInspectionMappingDto dto) {
        return storeInspectionMappingService.updateMapping(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMapping(@PathVariable int id) {
        storeInspectionMappingService.deleteMapping(id);
        return ResponseEntity.noContent().build();
    }
}
