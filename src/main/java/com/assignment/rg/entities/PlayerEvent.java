package com.assignment.rg.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "game_event_id", nullable = false)
    private GameEvent gameEvent;

    @Column(nullable = false)
    private String participationStatus; // need to manaeg later : registered or completed or dropped out. can be diff entity. 
}
