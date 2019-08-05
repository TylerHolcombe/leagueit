package com.tylerholcombe.leagueit.league.rest;

import com.tylerholcombe.leagueit.league.data.league.League;
import com.tylerholcombe.leagueit.league.data.league.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leagues")
public class LeagueController {
    @Autowired
    LeagueService leagueService;

    @PostMapping
    public League createLeague(League league) {
        //TODO: Validate league info
        return leagueService.createLeague(league);
    }
}
