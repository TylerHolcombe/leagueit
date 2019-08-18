package com.tylerholcombe.leagueit.league.rest;

import com.tylerholcombe.leagueit.league.data.Player;
import com.tylerholcombe.leagueit.league.data.league.League;
import com.tylerholcombe.leagueit.league.data.league.LeagueService;
import com.tylerholcombe.leagueit.user.data.ApplicationUser;
import com.tylerholcombe.leagueit.user.data.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/leagues")
public class LeagueController {
    @Autowired
    LeagueService leagueService;

    @Autowired
    UserService userService;

    @PostMapping
    public League createLeague(Authentication authentication, @RequestBody League league) {
        //TODO: Validate league info
        //TODO: Validate league owner matches userId
        league.setOwner(getActiveUser(authentication));
        return leagueService.createLeague(league);
    }

    @GetMapping("/{leagueId}")
    public League getLeague(@PathVariable("leagueId") Long leagueId) {
        //TODO: Validate request has access to league
        Optional<League> response = leagueService.findLeagueById(leagueId);
        if (!response.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return response.get();
    }

    @PutMapping("/{leagueId}/players/{userId}")
    public Player createPlayer(@PathVariable("leagueId") Long leagueId, @PathVariable Long userId) {
        //TODO: Validate request has access to league
        //TODO: Validate the User is unique within the League
        return leagueService.createPlayer(leagueId, userId);
    }

    private ApplicationUser getActiveUser(Authentication authentication) {
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            ApplicationUser user;
            if (principal != null) {
                String username;
                if (principal instanceof UserDetails) {
                    username = ((UserDetails) principal).getUsername();
                } else if (principal instanceof String) {
                    username = (String) principal;
                } else {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
                }
                user = userService.loadApplicationUserByUsername(username);
                return user;
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }
}
