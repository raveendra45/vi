package com.aiims.inspectionplan.Dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL) // Exclude null fields in JSON serialization
public class NeutralParameterDto {
    private int id;
    private String nameOfStore;
    private String storeType;
    private String assetType;
    private String assetCategory;
    private String subCategory;
    private String parentStore;
    private int nsnNumber;
    private int astsNumber;
    private int dcanNumber;
    private byte[] imageData; // Depending on use case, this might be a URL or other reference
    private int shelfLife;
    private int operationalLife;
    private int extendableLife;
    private int proofPeriodicity;
    private int testPeriodicity;
    private int maintenancePeriodicity;
    private String typeOfProcurement;
    private String supplierName;
    private int oemPartNumber;
    private int indigenousPartNumber;
    private Boolean indigenised;
    private String testEquipment;
    private int hazardLevel;
    private String compatibilityGroup;
    private String checklistRefNumber;
    private String ahsp;
    private Boolean itemStandard;
    private List<StoreInspectionMappingDto> storeInspectionMapping; // Reference to mapped data (optional)
}
