package com.aiims.inspectionplan.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "inspectionStages")
public class InspectionStages {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="inspectionStageId")
	private int id;
	
	private String inspectionStageName;
	private int inspectionStageSequence;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // Adjust cascade type as needed
    @JoinColumn(name = "InspectionMappingId") // Using the unique inspection mapping store ID
    private StoreInspectionMapping storeInspectionMapping;
    
    // Adjust mappedBy based on your InspectionSection entity
    @OneToMany(mappedBy = "inspectionStages", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InspectionSection> inspectionSection; // This is the list of childrendren
}
