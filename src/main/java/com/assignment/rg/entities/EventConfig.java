package com.assignment.rg.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id") 
    private List<Reward> rewards; 
    
    @Column(nullable = false)
    private long minRankRequired; 

    @Column(nullable = false)
    private int minLevelRequired; 

    @Column(nullable = false)
    private int maxParticipants;  

    @OneToOne
    @JoinColumn(name = "game_event_id", nullable = false, unique = true)
    private GameEvent gameEvent;

}
