package com.assignment.rg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.rg.entities.GameType;

@Repository
public interface GameTypeRepository extends JpaRepository<GameType, Long>{

}
