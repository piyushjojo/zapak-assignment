package com.assignment.rg.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerScoreDTO {

	private Long playerId;
    private Long gameId;
    private Long gameTypeId;
    private Long score;
    private LocalDateTime timestamp;
}
