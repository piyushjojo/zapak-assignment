package com.assignment.rg.dto;

import com.assignment.rg.entities.Game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerLeaderBoardDTO {

    private Game game;
    private String countryCd;
}
