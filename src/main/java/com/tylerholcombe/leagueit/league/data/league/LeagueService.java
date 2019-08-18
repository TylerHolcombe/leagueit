package com.tylerholcombe.leagueit.league.data.league;

import com.tylerholcombe.leagueit.league.data.Player;
import com.tylerholcombe.leagueit.league.data.PlayerRepository;
import com.tylerholcombe.leagueit.user.data.ApplicationUser;
import com.tylerholcombe.leagueit.user.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class LeagueService {
    @Autowired
    LeagueRepository leagueRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PlayerRepository playerRepository;

    public League createLeague(League league) {
        return leagueRepository.save(league);
    }

    public Optional<League> findLeagueById(Long leagueId) {
        return leagueRepository.findById(leagueId);
    }

    public Player createPlayer(Long leagueId, Long userId) {
        Optional<ApplicationUser> user = userRepository.findById(userId);
        Optional<League> league = leagueRepository.findById(leagueId);
        if (!user.isPresent() || !league.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Player player = new Player();
        player.setUser(user.get());
        player.setLeague(league.get());
        return playerRepository.save(player);
    }
}
