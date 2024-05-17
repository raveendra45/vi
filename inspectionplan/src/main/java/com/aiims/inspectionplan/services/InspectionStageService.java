package com.aiims.inspectionplan.services;

import com.aiims.inspectionplan.Dto.InspectionStagesDto;
import com.aiims.inspectionplan.Dto.StoreInspectionMappingDto;
import com.aiims.inspectionplan.entity.InspectionStages;
import com.aiims.inspectionplan.entity.StoreInspectionMapping;
import com.aiims.inspectionplan.repository.InspectionStagesRepository;
import com.aiims.inspectionplan.repository.StoreInspectionMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InspectionStageService {

    @Autowired
    private InspectionStagesRepository inspectionStagesRepository;

    @Autowired
    private StoreInspectionMappingRepository storeInspectionMappingRepository;

    public List<InspectionStagesDto> getAllInspectionStages() {
        List<InspectionStages> inspectionStagesList = inspectionStagesRepository.findAll();
        return inspectionStagesList.stream()
                .map(InspectionStagesDto::new)
                .collect(Collectors.toList());
    }

    public Optional<InspectionStagesDto> getInspectionStageById(int id) {
        Optional<InspectionStages> inspectionStageOptional = inspectionStagesRepository.findById(id);
        return inspectionStageOptional.map(InspectionStagesDto::new);
    }

    public InspectionStagesDto saveInspectionStage(InspectionStagesDto inspectionStagesDto) {
        InspectionStages inspectionStage = new InspectionStages();

	        // Map properties from DTO to entity
	        inspectionStage.setId(inspectionStagesDto.getId());
	        inspectionStage.setInspectionStageName(inspectionStagesDto.getInspectionStageName());
	        inspectionStage.setInspectionStageSequence(inspectionStagesDto.getInspectionStageSequence());

        // Map the nested StoreInspectionMappingDto to StoreInspectionMapping entity
        if (inspectionStagesDto.getStoreInspectionMapping() != null) {
            Optional<StoreInspectionMapping> storeInspectionMappingOptional = storeInspectionMappingRepository.findById(inspectionStagesDto.getStoreInspectionMapping().getId());
            storeInspectionMappingOptional.ifPresent(inspectionStage::setStoreInspectionMapping);
        }

        // Save the entity
        InspectionStages savedInspectionStage = inspectionStagesRepository.save(inspectionStage);

        // Convert saved entity to DTO and return
        return new InspectionStagesDto(savedInspectionStage);
    }

    public void deleteInspectionStage(int id) {
        inspectionStagesRepository.deleteById(id);
    }
}
