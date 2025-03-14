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
public class GameEventDTO {
    private Long id;
    private String eventName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;  // "SCHEDULED", "ONGOING", "COMPLETED"
    private Long gameTypeId;  // Links to GameType
    private EventConfigDTO eventConfig; // Embedded Configuration DTO
}
