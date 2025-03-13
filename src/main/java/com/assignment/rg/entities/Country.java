package com.assignment.rg.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Country {
	
	@Id
	@Column(nullable = false, unique = true)
	private String countryCd;
	
	@Column(nullable = false, unique = true)
	private String countryName ; 
	
	@Column(nullable = false)
	private String active; 

}
