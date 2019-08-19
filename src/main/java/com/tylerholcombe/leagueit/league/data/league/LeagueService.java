package com.tylerholcombe.leagueit.league.data.league;

import com.tylerholcombe.leagueit.league.data.Player;
import com.tylerholcombe.leagueit.league.data.PlayerRepository;
import com.tylerholcombe.leagueit.league.rest.LeagueDto;
import com.tylerholcombe.leagueit.league.rest.PlayerDto;
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

    public LeagueDto createLeague(LeagueDto leagueDto) {
        leagueDto.setLeagueId(null);
        League league = new League(leagueDto);
        league = leagueRepository.save(league);

        return new LeagueDto(league);
    }

    public LeagueDto findLeagueById(Long leagueId, Long applicationUserId) {
        Optional<League> response = leagueRepository.findById(leagueId);
        if (!response.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "League was not found");
        }
        if (!isUserInLeague(response.get(), applicationUserId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User does not have access to this league");
        }

        return new LeagueDto(response.get());
    }

    public PlayerDto createPlayer(Long leagueId, Long playerId, Long userId) {
        Optional<ApplicationUser> user = userRepository.findById(playerId);
        Optional<League> league = leagueRepository.findById(leagueId);
        if (!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User was not found");
        }
        if (!league.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "League was not found");
        }
        if (!canUserModifyLeague(league.get(), userId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User does not have access to add a new player");
        }

        Player player = new Player();
        player.setUser(user.get());
        player.setLeague(league.get());
        //TODO: get default rating for each rating_strategy
        player.setRating(1200.0);
        player.setWins(0);
        player.setLosses(0);
        player.setTies(0);
        player = playerRepository.save(player);
        return new PlayerDto(player);
    }

    private boolean isUserInLeague(League league, Long userId) {
        if (league == null || userId == null) {
            return false;
        }

        boolean isAuthorized = false;
        if (userId.equals(league.getOwnerId())) {
            isAuthorized = true;
        } else {
            for (Player player : league.getPlayers()) {
                if (userId.equals(player.getPlayerId())) {
                    isAuthorized = true;
                }
            }
        }
        return isAuthorized;
    }

    private boolean canUserModifyLeague(League league, Long userId) {
        if (league == null || userId == null) {
            return false;
        }

        boolean isAuthorized = false;
        if (userId.equals(league.getOwnerId())) {
            isAuthorized = true;
        }
        //TODO: Add league admins and check for them here
        return isAuthorized;
    }
}
