package com.assignment.rg.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRegistrationDTO {

    private String deviceId;
    private String username;
    private Long platformId;
    private String countryCd;
}
