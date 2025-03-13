package com.assignment.rg.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.rg.entities.Currency;
import com.assignment.rg.entities.Player;
import com.assignment.rg.entities.PlayerCurrency;

@Repository
public interface PlayerCurrencyRepository extends JpaRepository<PlayerCurrency, Long> {

	Optional<PlayerCurrency> findByPlayerAndCurrency(Player player, Currency currency);

	List<PlayerCurrency> findByPlayer(Player player);


}
