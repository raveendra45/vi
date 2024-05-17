package com.aiims.inspectionplan.controllers;

import com.aiims.inspectionplan.Dto.UsageModeDto;
import com.aiims.inspectionplan.services.UsageModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usage-mode")
public class UsageModeControllers {

    private final UsageModeService usageModeService;

    @Autowired
    public UsageModeControllers(UsageModeService usageModeService) {
        this.usageModeService = usageModeService;
    }

    @PostMapping("/create")
    public ResponseEntity<UsageModeDto> createUsageMode(@RequestBody UsageModeDto usageModeDto) {
        UsageModeDto savedUsageModeDto = usageModeService.createUsageMode(usageModeDto);
        return new ResponseEntity<>(savedUsageModeDto, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UsageModeDto>> getAllUsageModes() {
        List<UsageModeDto> usageModeDtos = usageModeService.getAllUsageModes();
        return new ResponseEntity<>(usageModeDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsageModeDto> getUsageModeById(@PathVariable int id) {
        UsageModeDto usageModeDto = usageModeService.getUsageModeById(id);
        if (usageModeDto != null) {
            return new ResponseEntity<>(usageModeDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsageModeDto> updateUsageMode(@PathVariable int id, @RequestBody UsageModeDto usageModeDto) {
        UsageModeDto updatedUsageModeDto = usageModeService.updateUsageMode(id, usageModeDto);
        if (updatedUsageModeDto != null) {
            return new ResponseEntity<>(updatedUsageModeDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsageMode(@PathVariable int id) {
        boolean success = usageModeService.deleteUsageMode(id);
        if (success) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}