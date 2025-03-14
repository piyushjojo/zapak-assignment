package com.assignment.rg.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerProgressDTO {
    private Long playerId;
    private Long level;
    private Long rankId;
    private String countryCd;
    private LocalDateTime lastActive;
    private List<Long> rewardIds;
    private List<PlayerCurrencyDTO> currencies;
}
