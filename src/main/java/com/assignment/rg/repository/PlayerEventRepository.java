package com.assignment.rg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.rg.entities.PlayerEvent;

public interface PlayerEventRepository extends JpaRepository<PlayerEvent, Long> {

}
