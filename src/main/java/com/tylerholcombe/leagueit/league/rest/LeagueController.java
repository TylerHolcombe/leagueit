package com.tylerholcombe.leagueit.league.rest;

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

@RestController
@RequestMapping("/leagues")
public class LeagueController {
    @Autowired
    LeagueService leagueService;

    @Autowired
    UserService userService;

    @PostMapping
    public LeagueDto createLeague(Authentication authentication, @RequestBody LeagueDto league) {
        ApplicationUser owner = getActiveUser(authentication);
        league.setOwnerId(owner.getApplicationUserId());
        league.setOwnerUsername(owner.getUsername());
        return leagueService.createLeague(league);
    }

    @GetMapping("/{leagueId}")
    public LeagueDto getLeague(Authentication authentication, @PathVariable("leagueId") Long leagueId) {
        ApplicationUser user = getActiveUser(authentication);
        return leagueService.findLeagueById(leagueId, user.getApplicationUserId());
    }

    @PutMapping("/{leagueId}/players/{playerId}")
    public PlayerDto createPlayer(Authentication authentication, @PathVariable("leagueId") Long leagueId, @PathVariable("playerId") Long playerId) {
        ApplicationUser user = getActiveUser(authentication);
        //TODO: Validate the User is unique within the League -- catch/rethrow exception from DB
        return leagueService.createPlayer(leagueId, playerId, user.getApplicationUserId());
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
