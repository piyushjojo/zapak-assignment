package com.assignment.rg.dto;

import java.util.List;

import com.assignment.rg.entities.Reward;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventConfigDTO {
    private Long id;
    private List<Reward> rewards; 
    private long minRankRequired;
    private int minLevelRequired; 
    private int maxParticipants;
    
}
