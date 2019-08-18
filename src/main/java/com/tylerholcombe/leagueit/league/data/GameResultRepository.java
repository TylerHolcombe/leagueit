package com.tylerholcombe.leagueit.league.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameResultRepository extends CrudRepository<GameResult, Long> {
}
