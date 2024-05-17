package com.aiims.inspectionplan.services;

import com.aiims.inspectionplan.Dto.InspectionSectionDto;
import com.aiims.inspectionplan.Dto.InspectionStagesDto;
import com.aiims.inspectionplan.entity.InspectionChecksheet;
import com.aiims.inspectionplan.entity.InspectionSection;
import com.aiims.inspectionplan.entity.InspectionStages;
import com.aiims.inspectionplan.repository.InspectionChecksheetRepository;
import com.aiims.inspectionplan.repository.InspectionSectionRepository;
import com.aiims.inspectionplan.repository.InspectionStagesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InspectionSectionService {

    private final InspectionSectionRepository inspectionSectionRepository;
    private final InspectionStagesRepository inspectionStagesRepository;
    private final InspectionChecksheetRepository inspectionChecksheetRepository;

    public InspectionSectionService(InspectionSectionRepository inspectionSectionRepository, InspectionStagesRepository inspectionStagesRepository, InspectionChecksheetRepository inspectionChecksheetRepository) {
        this.inspectionSectionRepository = inspectionSectionRepository;
        this.inspectionStagesRepository = inspectionStagesRepository;
        this.inspectionChecksheetRepository = inspectionChecksheetRepository;
    }

    // Convert an entity to a DTO
    private InspectionSectionDto toDto(InspectionSection entity) {
        List<Integer> childSectionIds = entity.getChildren().stream()
                .map(InspectionSection::getId)
                .collect(Collectors.toList());
        List<Integer> inspectionChecksheetIds = entity.getInspectionChecksheet().stream()
                .map(InspectionChecksheet::getId)
                .collect(Collectors.toList());

        Integer parentSectionId = (entity.getParentSectionId() != null) ? entity.getParentSectionId().getId() : null;

        InspectionStagesDto inspectionStagesDto = null;
        if (entity.getInspectionStages() != null) {
            inspectionStagesDto = new InspectionStagesDto(entity.getInspectionStages());
        }

        return new InspectionSectionDto(
                entity.getId(),
                entity.getSectionName(),
                entity.getSectionSequence(),
                parentSectionId,
                inspectionStagesDto,
                childSectionIds,
                inspectionChecksheetIds
        );
    }

    // Convert a DTO to an entity
    private InspectionSection toEntity(InspectionSectionDto dto) {
        InspectionSection section = new InspectionSection();
        section.setId(dto.getId());
        section.setSectionName(dto.getSectionName());
        section.setSectionSequence(dto.getSectionSequence());

        if (dto.getParentSectionId() != null) {
            InspectionSection parentSection = inspectionSectionRepository.findById(dto.getParentSectionId())
                    .orElseThrow(() -> new IllegalArgumentException("Parent section not found"));
            section.setParentSectionId(parentSection);
        }

        if (dto.getInspectionStageDto() != null) {
            InspectionStages inspectionStages = inspectionStagesRepository.findById(dto.getInspectionStageDto().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Inspection stage not found"));
            section.setInspectionStages(inspectionStages);
        }

        // Fetch and set child sections and inspection checksheets if needed
        List<InspectionSection> childSections = dto.getChildSectionIds().stream()
                .map(id -> inspectionSectionRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Child section not found")))
                .collect(Collectors.toList());
        section.setChildren(childSections);

        List<InspectionChecksheet> inspectionChecksheets = dto.getInspectionChecksheetIds().stream()
                .map(id -> inspectionChecksheetRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Inspection checksheet not found")))
                .collect(Collectors.toList());
        section.setInspectionChecksheet(inspectionChecksheets);

        return section;
    }

    public List<InspectionSectionDto> findAll() {
        return inspectionSectionRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Optional<InspectionSectionDto> findById(int id) {
        return inspectionSectionRepository.findById(id)
                .map(this::toDto);
    }

    public InspectionSectionDto create(InspectionSectionDto dto) {
        InspectionSection section = toEntity(dto);
        section = inspectionSectionRepository.save(section);
        return toDto(section);
    }

    public Optional<InspectionSectionDto> update(int id, InspectionSectionDto dto) {
        if (!inspectionSectionRepository.existsById(id)) {
            return Optional.empty();
        }
        InspectionSection section = toEntity(dto);
        section = inspectionSectionRepository.save(section);
        return Optional.of(toDto(section));
    }

    public boolean delete(int id) {
        if (!inspectionSectionRepository.existsById(id)) {
            return false;
        }
        inspectionSectionRepository.deleteById(id);
        return true;
    }
}
