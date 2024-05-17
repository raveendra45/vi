package com.aiims.inspectionplan.Dto;

import com.aiims.inspectionplan.entity.InspectionTable;
import com.aiims.inspectionplan.entity.NeutralParameter;
import com.aiims.inspectionplan.entity.StoreInspectionMapping;
import com.aiims.inspectionplan.entity.UsageMode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StoreInspectionMappingDto {
    private int id;
    private int inspectionId;
    private String inspectionName; // Assuming you want to include inspection name
    private int neutralParameterId;
    private String nameOfStore; // Assuming you want to include neutral parameter name
    private int usageModeId;
    private String usageModeName; // Assuming you want to include usage mode name
    
    
    public StoreInspectionMappingDto() {
        // Initialize any fields if needed
    }

    // A constructor to initialize the DTO from the entity
    public StoreInspectionMappingDto(StoreInspectionMapping storeInspectionMapping) {
        this.id = storeInspectionMapping.getId();
        
        if (storeInspectionMapping.getInspectionTable() != null) {
            this.inspectionId = storeInspectionMapping.getInspectionTable().getId();
            this.inspectionName = storeInspectionMapping.getInspectionTable().getInspectionName(); // Example field
        }
        
        if (storeInspectionMapping.getNeutralParameter() != null) {
            this.neutralParameterId = storeInspectionMapping.getNeutralParameter().getId();
            this.nameOfStore = storeInspectionMapping.getNeutralParameter().getNameOfStore(); // Example field
        }
        
        if (storeInspectionMapping.getUsageMode() != null) {
            this.usageModeId = storeInspectionMapping.getUsageMode().getId();
            this.usageModeName = storeInspectionMapping.getUsageMode().getUsageMode(); // Example field
        }
        
    }
    
    
}
