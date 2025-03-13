package com.assignment.rg.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reward {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long rewardId; 
	
	@Column(nullable = false, unique = true)
	private String rewardName ; 
	
	@Column(nullable = false)
	private String active; 
	
}
