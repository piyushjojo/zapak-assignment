package com.assignment.rg.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlatformDTO {
    private Long platformId;
    private String platformName;
}
