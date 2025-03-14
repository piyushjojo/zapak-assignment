package com.assignment.rg.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameEventResponseDTO {
    private Long id;
    private String eventName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private String gameTypeName; 
    private EventConfigDTO eventConfig; 
}
