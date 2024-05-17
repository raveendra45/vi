package com.aiims.inspectionplan.Dto;

import java.util.List;
import java.util.stream.Collectors;

import com.aiims.inspectionplan.entity.InspectionChecksheet;
import com.aiims.inspectionplan.entity.InspectionSection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InspectionSectionDto {
    private int id;
    private String sectionName;
    private int sectionSequence;
    private Integer parentSectionId; // Reference to parent section by ID
    private InspectionStagesDto inspectionStageDto; // Full details of the Inspection Stage
    private List<Integer> childSectionIds; // Child section IDs
    private List<Integer> inspectionChecksheetIds; // Inspection checksheet IDs

//    public InspectionSectionDto(InspectionSection entity) {
//        this.id = entity.getId();
//        this.sectionName = entity.getSectionName();
//        this.sectionSequence = entity.getSectionSequence();
        public InspectionSectionDto(InspectionSection entity,int id, String sectionName, int sectionSequence, Integer parentSectionId, InspectionStagesDto inspectionStageDto, List<Integer> childSectionIds, List<Integer> inspectionChecksheetIds) {
            this.id = entity.getId();
            this.sectionName = entity.getSectionName();
            this.sectionSequence = sectionSequence;
            this.parentSectionId = parentSectionId;
            this.inspectionStageDto = inspectionStageDto;
            this.childSectionIds = childSectionIds;
            this.inspectionChecksheetIds = inspectionChecksheetIds;
        

        if (entity.getParentSectionId() != null) {
            this.parentSectionId = entity.getParentSectionId().getId();
        }

        if (entity.getInspectionStages() != null) {
            this.inspectionStageDto = new InspectionStagesDto(entity.getInspectionStages());
        }

        if (entity.getChildren() != null) {
            this.childSectionIds = entity.getChildren().stream()
                    .map(InspectionSection::getId)
                    .collect(Collectors.toList());
        }

        if (entity.getInspectionChecksheet() != null) {
            this.inspectionChecksheetIds = entity.getInspectionChecksheet().stream()
                    .map(InspectionChecksheet::getId)
                    .collect(Collectors.toList());
        }
    }
}
