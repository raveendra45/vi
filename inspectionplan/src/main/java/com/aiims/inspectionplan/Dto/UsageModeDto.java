package com.aiims.inspectionplan.Dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsageModeDto{
    private int id;  // Corresponds to the `usageId` in the entity
    private String usageMode;  // Name of the usage mode
}