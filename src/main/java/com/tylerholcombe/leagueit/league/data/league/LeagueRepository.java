package com.tylerholcombe.leagueit.league.data.league;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeagueRepository extends CrudRepository<League, Long> {
    List<League> findByPlayers_User_Username(String username);
}
