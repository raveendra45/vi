package com.aiims.inspectionplan.entity;


import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "storeInspectionMapping")
public class StoreInspectionMapping {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="InspectionMappingId")
private int id;

@ManyToOne(fetch = FetchType.LAZY) // Establishing the relationship
@JoinColumn(name = "inspectionId") // Using the unique inspection id
private InspectionTable inspectionTable;

@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST) // Adjust cascade type as needed
@JoinColumn(name = "id") // Using the name of store
private NeutralParameter neutralParameter;

@ManyToOne(fetch = FetchType.LAZY) // Establishing the relationship
@JoinColumn(name = "usageId") // Using the unique requisition number
private UsageMode usageMode;


@OneToMany(mappedBy = "storeInspectionMapping", cascade = CascadeType.ALL, orphanRemoval = true)
private List<InspectionStages> inspectionStages; // This is the list of children


}
