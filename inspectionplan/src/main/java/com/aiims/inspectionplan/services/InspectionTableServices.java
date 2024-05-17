package com.aiims.inspectionplan.services;

import com.aiims.inspectionplan.Dto.InspectionTableDto;
import com.aiims.inspectionplan.entity.InspectionTable;
import com.aiims.inspectionplan.repository.InspectionTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service  // Indicates that this class provides business logic
public class InspectionTableServices {

    @Autowired
    private InspectionTableRepository inspectionRepository;
    
    
    private InspectionTableDto toDto(InspectionTable inspectionTable) {
        InspectionTableDto dto = new InspectionTableDto();
        dto.setId(inspectionTable.getId());
        dto.setInspectionName(inspectionTable.getInspectionName());
        // Convert related entities as needed (StoreInspectionMappingDto, InspectionChecksheetDto)
        return dto;
    }

    // Convert an InspectionTableDto to InspectionTable entity
    private InspectionTable toEntity(InspectionTableDto inspectionTableDto) {
        InspectionTable inspectionTable = new InspectionTable();
        inspectionTable.setId(inspectionTableDto.getId());
        inspectionTable.setInspectionName(inspectionTableDto.getInspectionName());
        // Handle related entities as needed
        return inspectionTable;
    }

    public InspectionTableDto createInspectionTable(InspectionTableDto inspectionTableDto) {
        InspectionTable inspectionTable = toEntity(inspectionTableDto);
        inspectionTable = inspectionRepository.save(inspectionTable);
        return toDto(inspectionTable);
    }

    public List<InspectionTableDto> getAllInspectionTables() {
        List<InspectionTable> inspectionTables = inspectionRepository.findAll();
        return inspectionTables.stream().map(this::toDto).collect(Collectors.toList());
    }

    public InspectionTableDto getInspectionTableById(int id) {
        InspectionTable inspectionTable = inspectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inspection table not found with id: " + id));
        return toDto(inspectionTable);
    }
    
    
    

    public List<InspectionTableDto> getAllInspections() {
        List<InspectionTable> inspections = inspectionRepository.findAll();
        return inspections.stream()  // Convert the list of entities to a list of DTOs
                          .map(this::convertToDto)
                          .collect(Collectors.toList());
    }

    public Optional<InspectionTableDto> getInspectionById(int id) {
        return inspectionRepository.findById(id).map(this::convertToDto);
    }

    public InspectionTableDto createInspection(InspectionTableDto dto) {
        InspectionTable newInspection = convertToEntity(dto);
        inspectionRepository.save(newInspection);
        return convertToDto(newInspection);
    }

    public Optional<InspectionTableDto> updateInspection(int id, InspectionTableDto dto) {
        Optional<InspectionTable> optionalInspection = inspectionRepository.findById(id);

        if (optionalInspection.isPresent()) {
            InspectionTable existingInspection = optionalInspection.get();
            existingInspection.setInspectionName(dto.getInspectionName());
            // If other fields need updating, set them here
            inspectionRepository.save(existingInspection);
            return Optional.of(convertToDto(existingInspection));
        }

        return Optional.empty();  // If the inspection doesn't exist, return empty
    }

    public boolean deleteInspection(int id) {
        if (inspectionRepository.existsById(id)) {
            inspectionRepository.deleteById(id);
            return true;  // Return true if the deletion was successful
        }
        return false;  // Return false if the ID doesn't exist
    }

    private InspectionTableDto convertToDto(InspectionTable inspection) {
        return new InspectionTableDto(
            inspection.getId(),
            inspection.getInspectionName(),
            // If there are related entities to include, add them here
            null,
            null
        );
    }

    private InspectionTable convertToEntity(InspectionTableDto dto) {
        return new InspectionTable(
            dto.getId(),
            dto.getInspectionName(),
            null,  // If there are related entities to include, populate them here
            null
        );
    }
}
