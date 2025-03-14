package com.assignment.rg.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerLeaderBoardDTO {

	private Long playerId;
    private String username;
    private Long gameId;
    private Long score;
    private String countryCd;
}
