package com.tylerholcombe.leagueit.league.rest;

import com.tylerholcombe.leagueit.league.data.league.League;
import com.tylerholcombe.leagueit.league.data.league.rating.RatingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LeagueDto {
    private Long leagueId;
    private String leagueName;
    private String leagueDescription;
    private Integer teamSize;
    private RatingStrategy ratingStrategy;
    private Long ownerId;
    private String ownerUsername;
    private Integer numPlayers;
    // Include basic player information
    private List<PlayerDto> players;

    public LeagueDto() {
    }

    public LeagueDto(League league) {
        this.leagueId = league.getLeagueId();
        this.leagueName = league.getLeagueName();
        this.leagueDescription = league.getLeagueDescription();
        this.teamSize = league.getTeamSize();
        this.ratingStrategy = league.getRatingStrategy();
        this.ownerId = league.getOwnerId();

        this.getPlayers().addAll(league.getPlayers().stream().map(PlayerDto::new).collect(Collectors.toList()));
        this.numPlayers = this.getPlayers().size();
    }

    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getLeagueDescription() {
        return leagueDescription;
    }

    public void setLeagueDescription(String leagueDescription) {
        this.leagueDescription = leagueDescription;
    }

    public Integer getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(Integer teamSize) {
        this.teamSize = teamSize;
    }

    public RatingStrategy getRatingStrategy() {
        return ratingStrategy;
    }

    public void setRatingStrategy(RatingStrategy ratingStrategy) {
        this.ratingStrategy = ratingStrategy;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public List<PlayerDto> getPlayers() {
        if(players == null) {
            players = new ArrayList<>();
        }
        return players;
    }

    public void setPlayers(List<PlayerDto> players) {
        this.players = players;
    }
}
