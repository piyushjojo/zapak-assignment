package com.assignment.rg.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Platform {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long platformId; 
	
	@Column(nullable = false, unique = true )
	private String platformName ; 
	
	@Column(nullable = false)
	private String active; 

}
