package com.assignment.rg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.rg.entities.EventConfig;

public interface EventConfigRepository extends JpaRepository<EventConfig, Long> {

	Optional<EventConfig> findByGameEventId(Long eventId);
	
}
