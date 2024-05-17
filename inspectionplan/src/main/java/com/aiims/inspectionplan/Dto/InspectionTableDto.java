

package com.aiims.inspectionplan.Dto;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InspectionTableDto {
    private int id;  // Corresponds to the inspectionId in the entity
    private String inspectionName;  // Name of the inspection

    // Example of including related entities
    private List<StoreInspectionMappingDto> storeInspectionMappings;
    private List<InspectionChecksheetDto> inspectionChecksheets;
}