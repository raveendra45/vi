package com.aiims.inspectionplan.services;

import com.aiims.inspectionplan.Dto.NeutralParameterDto;
import com.aiims.inspectionplan.entity.NeutralParameter; // Assuming you have an entity class
import com.aiims.inspectionplan.repository.NeutralParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NetralParameterService {

    private final NeutralParameterRepository neutralParameterRepository;

    @Autowired
    public NetralParameterService(NeutralParameterRepository neutralParameterRepository) {
        this.neutralParameterRepository = neutralParameterRepository;
    }

    // Create
    public NeutralParameterDto createNeutralParameter(NeutralParameterDto dto) {
        NeutralParameter entity = convertDtoToEntity(dto);
        NeutralParameter savedEntity = neutralParameterRepository.save(entity);
        return convertEntityToDto(savedEntity);
    }

    // Read
    public NeutralParameterDto getNeutralParameterById(int id) {
        Optional<NeutralParameter> optionalEntity = neutralParameterRepository.findById(id);
        return optionalEntity.map(this::convertEntityToDto).orElse(null);
    }

    public List<NeutralParameterDto> getAllNeutralParameters() {
        List<NeutralParameter> entities = neutralParameterRepository.findAll();
        return convertEntitiesToDtos(entities);
    }

    // Update
    public NeutralParameterDto updateNeutralParameter(int id, NeutralParameterDto dto) {
        Optional<NeutralParameter> optionalEntity = neutralParameterRepository.findById(id);
        if (optionalEntity.isPresent()) {
            NeutralParameter entity = optionalEntity.get();
            updateEntityWithDto(entity, dto);
            NeutralParameter updatedEntity = neutralParameterRepository.save(entity);
            return convertEntityToDto(updatedEntity);
        } else {
            return null;
        }
    }

    // Validate
    public boolean validateNeutralParameter(NeutralParameterDto dto) {
        // Example validation: Ensure required fields are not null
        return dto.getNameOfStore() != null && dto.getStoreType() != null;
    }

    // Evaluate
    public String evaluateNeutralParameter(int id) {
        NeutralParameterDto dto = getNeutralParameterById(id);
        if (dto == null) {
            return "Not Found";
        }

        // Example evaluation: Check hazard level
        if (dto.getHazardLevel() > 5) {
            return "High Hazard";
        } else {
            return "Low Hazard";
        }
    }

    // Utility functions to convert between DTO and Entity
    private NeutralParameter convertDtoToEntity(NeutralParameterDto dto) {
        NeutralParameter entity = new NeutralParameter();
        entity.setId(dto.getId());
        entity.setNameOfStore(dto.getNameOfStore());
        entity.setStoreType(dto.getStoreType());
        entity.setAssetType(dto.getAssetType());
        entity.setAssetCategory(dto.getAssetCategory());
        entity.setSubCategory(dto.getSubCategory());
        entity.setParentStore(dto.getParentStore());
        entity.setNsnNumber(dto.getNsnNumber());
        entity.setAstsNumber(dto.getAstsNumber());
        entity.setDcanNumber(dto.getDcanNumber());
        entity.setImageData(dto.getImageData());
        entity.setShelfLife(dto.getShelfLife());
        entity.setOperationalLife(dto.getOperationalLife());
        entity.setExtendableLife(dto.getExtendableLife());
        entity.setProofPeriodicity(dto.getProofPeriodicity());
        entity.setTestPeriodicity(dto.getTestPeriodicity());
        entity.setMaintenancePeriodicity(dto.getMaintenancePeriodicity());
        entity.setTypeOfProcurement(dto.getTypeOfProcurement());
        entity.setSupplierName(dto.getSupplierName());
        entity.setOemPartNumber(dto.getOemPartNumber());
        entity.setIndigenousPartNumber(dto.getIndigenousPartNumber());
        entity.setIndigenised(dto.getIndigenised());
        entity.setTestEquipment(dto.getTestEquipment());
        entity.setHazardLevel(dto.getHazardLevel());
        entity.setCompatibilityGroup(dto.getCompatibilityGroup());
        entity.setChecklistRefNumber(dto.getChecklistRefNumber());
        entity.setAhsp(dto.getAhsp());
        entity.setItemStandard(dto.getItemStandard());
        return entity;
    }

    private NeutralParameterDto convertEntityToDto(NeutralParameter entity) {
        NeutralParameterDto dto = new NeutralParameterDto();
        dto.setId(entity.getId());
        dto.setNameOfStore(entity.getNameOfStore());
dto.setStoreType(entity.getStoreType());
dto.setAssetType(entity.getAssetType());
dto.setAssetCategory(entity.getAssetCategory());
dto.setSubCategory(entity.getSubCategory());
dto.setParentStore(entity.getParentStore());
dto.setNsnNumber(entity.getNsnNumber());
dto.setAstsNumber(entity.getAstsNumber());
dto.setDcanNumber(entity.getDcanNumber());
dto.setImageData(entity.getImageData());
dto.setShelfLife(entity.getShelfLife());
dto.setOperationalLife(entity.getOperationalLife());
dto.setExtendableLife(entity.getExtendableLife());
dto.setProofPeriodicity(dto.getProofPeriodicity());
dto.setTestPeriodicity(dto.getTestPeriodicity());
dto.setMaintenancePeriodicity(dto.getMaintenancePeriodicity());
dto.setTypeOfProcurement(dto.getTypeOfProcurement());
dto.setSupplierName(dto.getSupplierName());
dto.setOemPartNumber(dto.getOemPartNumber());
dto.setIndigenousPartNumber(dto.getIndigenousPartNumber());
dto.setIndigenised(dto.getIndigenised());
dto.setTestEquipment(dto.getTestEquipment());
dto.setHazardLevel(dto.getHazardLevel());
dto.setCompatibilityGroup(entity.getCompatibilityGroup());
dto.setChecklistRefNumber(entity.getChecklistRefNumber());
dto.setAhsp(entity.getAhsp());
dto.setItemStandard(entity.getItemStandard());
return dto;
}

private List<NeutralParameterDto> convertEntitiesToDtos(List<NeutralParameter> entities) {
return entities.stream()
.map(this::convertEntityToDto)
.collect(Collectors.toList());
}

private void updateEntityWithDto(NeutralParameter entity, NeutralParameterDto dto) {
entity.setNameOfStore(dto.getNameOfStore());
entity.setStoreType(dto.getStoreType());
entity.setAssetType(dto.getAssetType());
entity.setAssetCategory(dto.getAssetCategory());
entity.setSubCategory(dto.getSubCategory());
entity.setParentStore(dto.getParentStore());
entity.setNsnNumber(dto.getNsnNumber());
entity.setAstsNumber(dto.getAstsNumber());
entity.setDcanNumber(dto.getDcanNumber());
entity.setImageData(dto.getImageData());
entity.setShelfLife(dto.getShelfLife());
entity.setOperationalLife(dto.getOperationalLife());
entity.setExtendableLife(dto.getExtendableLife());
entity.setProofPeriodicity(dto.getProofPeriodicity());
entity.setTestPeriodicity(dto.getTestPeriodicity());
entity.setMaintenancePeriodicity(dto.getMaintenancePeriodicity());
entity.setTypeOfProcurement(dto.getTypeOfProcurement());
entity.setSupplierName(dto.getSupplierName());
entity.setOemPartNumber(dto.getOemPartNumber());
entity.setIndigenousPartNumber(dto.getIndigenousPartNumber());
entity.setIndigenised(dto.getIndigenised());
entity.setTestEquipment(dto.getTestEquipment());
entity.setHazardLevel(dto.getHazardLevel());
entity.setCompatibilityGroup(dto.getCompatibilityGroup());
entity.setChecklistRefNumber(dto.getChecklistRefNumber());
entity.setAhsp(dto.getAhsp());
entity.setItemStandard(dto.getItemStandard());
}
}
