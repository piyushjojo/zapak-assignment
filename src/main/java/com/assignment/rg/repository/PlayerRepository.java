package com.assignment.rg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.rg.entities.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
	
	public Player findByUsername(String username);

}
