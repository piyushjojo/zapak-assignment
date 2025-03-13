package com.assignment.rg.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerCurrencyDTO {
    private Long currencyId;
    private String currencyName;
    private Long amount;
}
