package com.assignment.rg.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RewardDTO {
    private Long rewardId;
    private String rewardName;
}
