package com.assignment.rg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.rg.entities.Reward;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {

}
