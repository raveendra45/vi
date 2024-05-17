package com.aiims.inspectionplan.entity;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString// Avoid infinite loop

@Entity
@Table(name = "neutralParameter")
public class NeutralParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "name_of_store")
    private String nameOfStore;
    
    @Column(name = "store_type")
    private String storeType;
    

    @Column(name = "asset_type")
    private String assetType;
    
    @Column(name = "asset_category")
    private String assetCategory;
    
    @Column(name = "sub_category")
    private String subCategory;
    
    @Column(name = "parent_store")
    private String parentStore;
    
    @Column(name = "nsn_number")
    private int nsnNumber;
    
    @Column(name = "asts_number")
    private int astsNumber;
    
    @Column(name = "dcan_number")
    private int dcanNumber;
    
    @Lob
    @Column(name = "image_data")
    private byte[] imageData; // You may need to store image path or binary data, adjust accordingly
    
    @Column(name = "shelf_life")
    private int shelfLife;
    
    @Column(name = "operational_life")
    private int operationalLife;
    
    @Column(name = "extendable_life")
    private int extendableLife;
    
    @Column(name = "proof_periodicity")
    private int proofPeriodicity;
    
    @Column(name = "test_periodicity")
    private int testPeriodicity;
    
    @Column(name = "maintenance_periodicity")
    private int maintenancePeriodicity;
    
    @Column(name = "type_of_procurement")
    private String typeOfProcurement;
    
    @Column(name = "supplier_name")
    private String supplierName;
    
    @Column(name = "oem_part_number")
    private int oemPartNumber;
    
    @Column(name = "indigenous_part_number")
    private int indigenousPartNumber;
    
    @Column(name = "indigenised")
    private Boolean indigenised;
    
    @Column(name = "test_equipment")
    private String testEquipment;
    
    @Column(name = "hazard_level")
    private int hazardLevel;
    
    @Column(name = "compatibility_group")
    private String compatibilityGroup;
    
    @Column(name = "checklist_ref_number")
    private String checklistRefNumber;
    
    @Column(name = "ahsp")
    private String ahsp;
    
    @Column(name = "item_standard")
    private Boolean itemStandard;
    
    @JsonIgnore
	@Transient
	@OneToMany(mappedBy = "neutralParameter", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) 
    private List<StoreInspectionMapping> storeInspectionMapping;
}
