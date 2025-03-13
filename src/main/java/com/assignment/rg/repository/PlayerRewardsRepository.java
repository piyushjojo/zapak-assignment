package com.assignment.rg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.rg.entities.Player;
import com.assignment.rg.entities.PlayerRewards;

@Repository
public interface PlayerRewardsRepository extends JpaRepository<PlayerRewards, Long> {

	List<PlayerRewards> findByPlayer(Player player);

}
