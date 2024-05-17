package com.aiims.inspectionplan.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString// Avoid infinite loop
@AllArgsConstructor

@Entity
@Table(name = "masterCheckpoints")
public class MasterCheckpoints {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "checkpointId")
	private int id;
	private String checkpointDescription;
	private float minimumValue;
	private float maximumValue;
	
	@ManyToOne(fetch = FetchType.LAZY) // Establishing the relationship
	@JoinColumn(name = "checksheetId") // Using the unique inspection id
	private InspectionChecksheet inspectionChecksheet;
	
	@ManyToOne(fetch = FetchType.LAZY) // Establishing the relationship
	@JoinColumn(name = "sectionId") // Using the unique inspection id
	private MasterChecksheetSection masterChecksheetSection;
}
