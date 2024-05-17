package com.aiims.inspectionplan.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = {"children"}) // Avoid infinite loop
@Entity
@Table(name = "inspectionSection")
public class InspectionSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    private int id;

  
    private String sectionName;

    private int sectionSequence;

    // Parent reference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentSectionId")
    private InspectionSection parentSectionId; // This is the self-referential part

    // Child references
    @JsonIgnore
    @OneToMany(mappedBy = "parentSectionId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InspectionSection> children; // This is the list of children
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspectionStageId")
    private InspectionStages inspectionStages; // This is the self-referential part
    @JsonIgnore
    @OneToMany(mappedBy = "inspectionSection", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InspectionChecksheet> inspectionChecksheet;
    
    public Integer getInspectionStageId() {
        return (inspectionStages != null) ? inspectionStages.getId() : null;
    }
    
    
}

