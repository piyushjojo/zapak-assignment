package com.assignment.rg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.rg.entities.Rank;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {

}
