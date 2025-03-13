package com.assignment.rg.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Currency {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long currencyId ; 
	
	@Column(nullable = false, unique = true)
	private String currencyName ; 
	
	private int minLevelRequired;
	
	private long defaultAmount;
	
	@Column(nullable = false)
	private String active; 
	
	
	
	
}
