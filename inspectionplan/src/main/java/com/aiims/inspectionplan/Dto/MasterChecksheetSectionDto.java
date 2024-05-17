package com.aiims.inspectionplan.Dto;

import java.util.List;

public class MasterChecksheetSectionDto {
    private int sectionId;
    private String checksheetSectionName;
    private Integer parentSectionId; // Use Integer for nullable values
    private List<Integer> childSectionIds;

    // Getters and setters
    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getChecksheetSectionName() {
        return checksheetSectionName;
    }

    public void setChecksheetSectionName(String checksheetSectionName) {
        this.checksheetSectionName = checksheetSectionName;
    }

    public Integer getParentSectionId() {
        return parentSectionId;
    }

    public void setParentSectionId(Integer parentSectionId) {
        this.parentSectionId = parentSectionId;
    }

    public List<Integer> getChildSectionIds() {
        return childSectionIds;
    }

    public void setChildSectionIds(List<Integer> childSectionIds) {
        this.childSectionIds = childSectionIds;
    }
}
