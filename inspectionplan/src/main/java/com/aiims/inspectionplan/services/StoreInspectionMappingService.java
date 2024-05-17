package com.aiims.inspectionplan.services;

import com.aiims.inspectionplan.Dto.StoreInspectionMappingDto;
import com.aiims.inspectionplan.entity.InspectionTable;
import com.aiims.inspectionplan.entity.NeutralParameter;
import com.aiims.inspectionplan.entity.StoreInspectionMapping;
import com.aiims.inspectionplan.entity.UsageMode;
import com.aiims.inspectionplan.repository.InspectionTableRepository;
import com.aiims.inspectionplan.repository.NeutralParameterRepository;
import com.aiims.inspectionplan.repository.StoreInspectionMappingRepository;
import com.aiims.inspectionplan.repository.UsageModeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreInspectionMappingService {


    private final StoreInspectionMappingRepository storeInspectionMappingRepository;
    private final NeutralParameterRepository neutralParameterRepository;
    private final UsageModeRepository usageModeRepository;
    private final InspectionTableRepository inspectionTableRepository; // Assuming you have a repository for UsageMode
    private final Logger logger = LoggerFactory.getLogger(StoreInspectionMappingService.class);

    @Autowired
    public StoreInspectionMappingService(StoreInspectionMappingRepository storeInspectionMappingRepository,
                                         NeutralParameterRepository neutralParameterRepository,
                                         UsageModeRepository usageModeRepository,
                                         InspectionTableRepository inspectionTableRepository) {
        this.storeInspectionMappingRepository = storeInspectionMappingRepository;
        this.neutralParameterRepository = neutralParameterRepository;
        this.usageModeRepository = usageModeRepository;
        this.inspectionTableRepository=inspectionTableRepository;
    }

    public List<StoreInspectionMappingDto> getAllMappings() {
        return storeInspectionMappingRepository.findAll()
            .stream()
            .map(StoreInspectionMappingDto::new)
            .collect(Collectors.toList());
    }

    public StoreInspectionMappingDto getMappingById(int id) {
        return storeInspectionMappingRepository.findById(id)
            .map(StoreInspectionMappingDto::new)
            .orElseThrow(() -> new RuntimeException("Mapping not found")); // You could use a custom exception
    }
//    public void yourMethod(int id) {
//        InspectionTable inspectionTable = inspectionTableRepository.findById(id)
//            .orElseThrow(() -> new RuntimeException("InspectionTable not found for ID: " + id));
//
//        // Now you can use the inspectionTable object as needed
//    }
    public StoreInspectionMappingDto createMapping( StoreInspectionMappingDto dto) {
        StoreInspectionMapping mapping = new StoreInspectionMapping();
        
        // Set InspectionTable based on DTO
        Optional<InspectionTable> inspectionTableOptional = inspectionTableRepository.findById(dto.getInspectionId());
        InspectionTable inspectionTable = inspectionTableOptional.orElseThrow(() -> new RuntimeException("InspectionTable not found for ID: " + dto.getInspectionId()));
        mapping.setInspectionTable(inspectionTable);

        // Fetch and set NeutralParameter based on selected ID
        Optional<NeutralParameter> optionalNeutralParameter = neutralParameterRepository.findById(dto.getNeutralParameterId());
        NeutralParameter neutralParameter = optionalNeutralParameter.orElseThrow(() -> new RuntimeException("Neutral Parameter not found for ID: " + dto.getNeutralParameterId()));
        mapping.setNeutralParameter(neutralParameter);

        // Fetch and set UsageMode based on selected ID
        Optional<UsageMode> optionalUsageMode = usageModeRepository.findById(dto.getUsageModeId());
        UsageMode usageMode = optionalUsageMode.orElseThrow(() -> new RuntimeException("Usage Mode not found for ID: " + dto.getUsageModeId()));
        mapping.setUsageMode(usageMode);

        // Save StoreInspectionMapping
        try {
            StoreInspectionMapping savedMapping = storeInspectionMappingRepository.save(mapping);
            return new StoreInspectionMappingDto(savedMapping);
        } catch (Exception e) {
            // Log the error with specific information
            logger.error("Error creating mapping: " + e.getMessage());
            throw e; // Re-throw the exception to propagate it
        }
    }

    public StoreInspectionMappingDto updateMapping(int id, StoreInspectionMappingDto dto) {
        StoreInspectionMapping mapping = storeInspectionMappingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Mapping not found"));
        
        // Update fields as necessary
        StoreInspectionMapping updatedMapping = storeInspectionMappingRepository.save(mapping);
        return new StoreInspectionMappingDto(updatedMapping);
    }

    public void deleteMapping(int id) {
        storeInspectionMappingRepository.deleteById(id);
    }
}
