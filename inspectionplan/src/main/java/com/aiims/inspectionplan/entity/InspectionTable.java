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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

	@Getter
	@Setter
	@ToString// Avoid infinite loop
	@AllArgsConstructor
	
	@Entity
	@Table(name = "inspectionTable")
	public class InspectionTable {
		
		
		public InspectionTable() {
			
			// TODO Auto-generated constructor stub
		}

		public InspectionTable(int id2, String inspectionName2) {
		this.id=id2;
		this.inspectionName =inspectionName2;
		}

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY) 
	    @Column(name = "inspectionId")
		public int id;
		public String inspectionName;
		
		
	
		@OneToMany(mappedBy = "inspectionTable", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) 
	    private List<StoreInspectionMapping> storeInspectionMapping;
		
		
	
		@OneToMany(mappedBy = "inspectionTable", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) 
	    private List<InspectionChecksheet> inspectionChecksheet;
	}

