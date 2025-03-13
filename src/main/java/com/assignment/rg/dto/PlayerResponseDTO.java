package com.assignment.rg.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerResponseDTO {
    private Long playerId;
    private String username;
    private Long level;
    private RankDTO rank;
    private PlatformDTO platform;
    private CountryDTO country;
    private String active;
    private LocalDateTime lastActive;
    private LocalDateTime modifiedDate;
    private LocalDateTime creationDate;
    private List<PlayerCurrencyDTO> currencies;
    private List<RewardDTO> rewards;
}
