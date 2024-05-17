package com.aiims.inspectionplan.Dto;

import com.aiims.inspectionplan.entity.InspectionStages;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class InspectionStagesDto {
    private int id;
    private String inspectionStageName;
    private int inspectionStageSequence;
    private StoreInspectionMappingDto storeInspectionMapping; // Updated to use StoreInspectionMappingDto
    private List<InspectionSectionDto> inspectionSections;

    public InspectionStagesDto(InspectionStages entity) {
        this.id = entity.getId();
        this.inspectionStageName = entity.getInspectionStageName();
        this.inspectionStageSequence = entity.getInspectionStageSequence();

        if (entity.getStoreInspectionMapping() != null) {
            this.storeInspectionMapping = new StoreInspectionMappingDto(entity.getStoreInspectionMapping());
        }

//        if (entity.getInspectionSection() != null) {
//            this.inspectionSections = entity.getInspectionSection().stream()
//                    .map(InspectionSectionDto::new)
//                    .collect(Collectors.toList());
//        }
    }
}
