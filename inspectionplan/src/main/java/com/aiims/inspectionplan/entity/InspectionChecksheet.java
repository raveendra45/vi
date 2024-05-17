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
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString// Avoid infinite loop

@Entity
@Table(name = "inspectionChecksheet")
public class InspectionChecksheet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "checksheetId")
	private int id;
	
	private String ChecksheetName;
	
	@ManyToOne(fetch = FetchType.LAZY) // Establishing the relationship
	@JoinColumn(name = "inspectionId") // Using the unique inspection id
	private InspectionTable inspectionTable;
	
	 @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "section_id")
	    private InspectionSection inspectionSection; // This is the self-referential part

//	 @JsonIgnore
//	 @OneToMany(mappedBy = "parentSectionId", cascade = CascadeType.ALL, orphanRemoval = true)
//	    private List<InspectionChecksheet> childSectionId; 
		
	 @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "inspectionStageId")
	    private InspectionStages inspectionStages;
	 
	 @JsonIgnore
	 @OneToMany(mappedBy = "inspectionChecksheet", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<MasterCheckpoints> masterCheckpoints; 
}
