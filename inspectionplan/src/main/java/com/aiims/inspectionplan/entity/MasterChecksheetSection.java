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
@Entity
@Table(name = "masterChecksheetSection")
public class MasterChecksheetSection {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "sectionId")
	private int id ;
	private String checksheetSectionName;
	
	@ManyToOne(fetch = FetchType.LAZY) //
	@JoinColumn(name = "checksheetId") // 
	private InspectionChecksheet inspectionChecksheet;
	
	@ManyToOne(fetch = FetchType.LAZY) // Establishing the relationship
	@JoinColumn(name = "parentSectionId") // Using the unique  id
	private MasterChecksheetSection parentSectionId;
	
	
	 
	 @JsonIgnore
	 @OneToMany(mappedBy = "parentSectionId", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<MasterChecksheetSection> childSectionId; 
	
	
	 @JsonIgnore
	 @OneToMany(mappedBy = "masterChecksheetSection", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<MasterCheckpoints> masterCheckpoints; 
}
