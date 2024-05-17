package com.aiims.inspectionplan.controllers;

import com.aiims.inspectionplan.Dto.NeutralParameterDto;
import com.aiims.inspectionplan.services.NetralParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/neutral-parameters")
public class NeutralParameterControllers {

    private final NetralParameterService neutralParameterService;

    @Autowired
    public NeutralParameterControllers(NetralParameterService neutralParameterService) {
        this.neutralParameterService = neutralParameterService;
    }

    // Create
    @PostMapping
    public ResponseEntity<NeutralParameterDto> createNeutralParameter(@RequestBody NeutralParameterDto dto) {
        if (!neutralParameterService.validateNeutralParameter(dto)) {
            return ResponseEntity.badRequest().body(null); // Validation failure
        }
        NeutralParameterDto createdDto = neutralParameterService.createNeutralParameter(dto);
        return ResponseEntity.ok(createdDto);
    }

    // Read
    @GetMapping("/{id}")
    public ResponseEntity<NeutralParameterDto> getNeutralParameterById(@PathVariable int id) {
        NeutralParameterDto dto = neutralParameterService.getNeutralParameterById(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build(); // Not found
        }
    }

    @GetMapping
    public ResponseEntity<List<NeutralParameterDto>> getAllNeutralParameters() {
        List<NeutralParameterDto> dtos = neutralParameterService.getAllNeutralParameters();
        return ResponseEntity.ok(dtos);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<NeutralParameterDto> updateNeutralParameter(@PathVariable int id, @RequestBody NeutralParameterDto dto) {
        NeutralParameterDto updatedDto = neutralParameterService.updateNeutralParameter(id, dto);
        if (updatedDto != null) {
            return ResponseEntity.ok(updatedDto);
        } else {
            return ResponseEntity.notFound().build(); // Not found
        }
    }

    // Evaluate
    @GetMapping("/{id}/evaluate")
    public ResponseEntity<String> evaluateNeutralParameter(@PathVariable int id) {
        String evaluationResult = neutralParameterService.evaluateNeutralParameter(id);
        return ResponseEntity.ok(evaluationResult);
    }
}
