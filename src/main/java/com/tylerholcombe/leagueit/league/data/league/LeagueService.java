package com.tylerholcombe.leagueit.league.data.league;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeagueService {
    @Autowired
    LeagueRepository leagueRepository;

    public League createLeague(League league) {
        return leagueRepository.save(league);
    }
}
