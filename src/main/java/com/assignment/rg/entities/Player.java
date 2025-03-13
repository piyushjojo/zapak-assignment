package com.assignment.rg.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@Column(nullable = false)
	private String deviceId; 
	
	@Column(nullable = false)
	private String username ;
	
	@ManyToOne
	@JoinColumn(name="platform_id", nullable=false)
	private Platform platformId;
	
	private long level ; 
	
	@ManyToOne
	@JoinColumn(name="rank_id", nullable=false)
	private Rank rank;
	
	@ManyToOne
	@JoinColumn(name="country_cd", nullable=false)
	private Country countryCd;
	
	@Column(nullable = false)
	private String active; 
	
	private LocalDateTime lastActive;
	
	@LastModifiedDate
	private LocalDateTime modifiedDate;
	
	@CreatedDate
	@Column(updatable = false)
    private LocalDateTime creationDate;
    
    
	
}
