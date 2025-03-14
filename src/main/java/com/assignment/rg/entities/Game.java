package com.assignment.rg.entities;

import java.time.LocalDateTime;

import com.assignment.rg.config.AuditConfig;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditConfig.class)
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long gameId ; 
	
	@ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;
	
	@ManyToOne
    @JoinColumn(name = "game_type_id", nullable = false)
	private GameType gameType;
	
    @Column(nullable = false)
    private long score;

	@Column(nullable = false)
	private LocalDateTime createdAt;
}
