package com.assignment.rg.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryDTO {
    private String countryCd;
    private String countryName;
}
