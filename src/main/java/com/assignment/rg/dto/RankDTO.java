package com.assignment.rg.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RankDTO {
    private Long rankId;
    private String rankName;
}
