package com.assignment.rg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.rg.entities.Player;
import com.assignment.rg.entities.PlayerScore;

@Repository
public interface PlayerScoreRepository extends JpaRepository<PlayerScore, Long> {

	List<PlayerScore> findByPlayer(Player player);
	
	@Query(value = "SELECT ps FROM PlayerScore ps where ps.gameType.id = :gameTypeId ORDER BY ps.game.score DESC LIMIT :limit")
    List<PlayerScore> findTopPlayers(@Param("limit") int limit, Long gameTypeId);

    @Query(value = "SELECT ps FROM PlayerScore ps WHERE ps.player.country.countryCd = :countryCd and  ps.gameType.id = :gameTypeId ORDER BY ps.game.score DESC LIMIT :limit")
    List<PlayerScore> findCountryWiseTopPlayer(@Param("countryCd") String countryCd, @Param("limit") int limit, Long gameTypeId);

}
