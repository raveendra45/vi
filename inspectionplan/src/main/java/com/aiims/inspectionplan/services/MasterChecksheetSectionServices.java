package com.aiims.inspectionplan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aiims.inspectionplan.entity.MasterChecksheetSection;
import com.aiims.inspectionplan.repository.MasterChecksheetSectionRepository;
import com.aiims.inspectionplan.Dto.MasterChecksheetSectionDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MasterChecksheetSectionServices {
    @Autowired
    private MasterChecksheetSectionRepository repository;

    public MasterChecksheetSectionDto toDTO(MasterChecksheetSection section) {
        MasterChecksheetSectionDto dto = new MasterChecksheetSectionDto();
        dto.setSectionId(section.getId());
        dto.setChecksheetSectionName(section.getChecksheetSectionName());
        dto.setParentSectionId(section.getParentSectionId() != null ? section.getParentSectionId().getId() : null);
        dto.setChildSectionIds(section.getChildSectionId().stream().map(MasterChecksheetSection::getId).collect(Collectors.toList()));
        return dto;
    }

    public MasterChecksheetSection fromDTO(MasterChecksheetSectionDto dto) {
        MasterChecksheetSection section = new MasterChecksheetSection();
        section.setId(dto.getSectionId());
        section.setChecksheetSectionName(dto.getChecksheetSectionName());
        return section;
    }

    public MasterChecksheetSectionDto create(MasterChecksheetSectionDto dto) {
        MasterChecksheetSection section = fromDTO(dto);
        if (dto.getParentSectionId() != null) {
            section.setParentSectionId(repository.findById(dto.getParentSectionId()).orElse(null));
        }
        section = repository.save(section);
        return toDTO(section);
    }

    public MasterChecksheetSectionDto findById(int id) {
        MasterChecksheetSection section = repository.findById(id).orElseThrow(() -> new RuntimeException("Section not found"));
        return toDTO(section);
    }

    public List<MasterChecksheetSectionDto> findAll() {
        List<MasterChecksheetSection> sections = repository.findAll();
        return sections.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public MasterChecksheetSectionDto update(int id, MasterChecksheetSectionDto dto) {
        MasterChecksheetSection existingSection = repository.findById(id).orElseThrow(() -> new RuntimeException("Section not found"));
        existingSection.setChecksheetSectionName(dto.getChecksheetSectionName());
        existingSection.setParentSectionId(dto.getParentSectionId() != null ? repository.findById(dto.getParentSectionId()).orElse(null) : null);
        existingSection = repository.save(existingSection);
        return toDTO(existingSection);
    }
}
