package com.aiims.inspectionplan.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InspectionChecksheetDto {
    private int id;
    private String checksheetName;

    private int inspectionId; // ID of the related InspectionTable
    private int sectionId; // ID of the related InspectionSection
    private int inspectionStageId; // ID of the related InspectionStages

    // You can add any other necessary fields or methods here
}
