package com.assignment.rg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.rg.entities.Game;
import com.assignment.rg.entities.Player;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {


	List<Game> findByPlayer(Player player);
	
	@Query(value = "SELECT g FROM Game g where g.gameType.id = :gameTypeId ORDER BY g.score DESC LIMIT :limit")
    List<Game> findTopPlayers(@Param("limit") int limit, @Param("gameTypeId") Long gameTypeId);

    @Query(value = "SELECT g FROM Game g WHERE g.player.country.countryCd = :countryCd and  g.gameType.id = :gameTypeId ORDER BY g.score DESC LIMIT :limit")
    List<Game> findCountryWiseTopPlayer(@Param("countryCd") String countryCd, @Param("limit") int limit, @Param("gameTypeId") Long gameTypeId);

}
