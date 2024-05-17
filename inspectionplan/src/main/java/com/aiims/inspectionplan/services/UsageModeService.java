package com.aiims.inspectionplan.services;

import com.aiims.inspectionplan.Dto.UsageModeDto;
import com.aiims.inspectionplan.entity.UsageMode;
import com.aiims.inspectionplan.repository.UsageModeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsageModeService {

    private final UsageModeRepository usageModeRepository;

    @Autowired
    public UsageModeService(UsageModeRepository usageModeRepository) {
        this.usageModeRepository = usageModeRepository;
    }

    public UsageModeDto createUsageMode(UsageModeDto usageModeDto) {
        UsageMode usageMode = new UsageMode();
        BeanUtils.copyProperties(usageModeDto, usageMode);
        usageMode = usageModeRepository.save(usageMode);
        UsageModeDto savedDto = new UsageModeDto();
        BeanUtils.copyProperties(usageMode, savedDto);
        return savedDto;
    }

    public List<UsageModeDto> getAllUsageModes() {
        List<UsageMode> usageModes = usageModeRepository.findAll();
        return usageModes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public UsageModeDto getUsageModeById(int id) {
        Optional<UsageMode> optionalUsageMode = usageModeRepository.findById(id);
        return optionalUsageMode.map(this::convertToDto).orElse(null);
    }

    public UsageModeDto updateUsageMode(int id, UsageModeDto usageModeDto) {
        Optional<UsageMode> optionalUsageMode = usageModeRepository.findById(id);
        if (optionalUsageMode.isPresent()) {
            UsageMode usageMode = optionalUsageMode.get();
            BeanUtils.copyProperties(usageModeDto, usageMode);
            usageMode.setId(id); // Ensure the correct ID is set
            usageMode = usageModeRepository.save(usageMode);
            return convertToDto(usageMode);
        }
        return null;
    }

    public boolean deleteUsageMode(int id) {
        if (usageModeRepository.existsById(id)) {
            usageModeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private UsageModeDto convertToDto(UsageMode usageMode) {
        UsageModeDto dto = new UsageModeDto();
        BeanUtils.copyProperties(usageMode, dto);
        return dto;
    }
}