package com.tylerholcombe.leagueit.league.rest;

import com.tylerholcombe.leagueit.league.data.league.League;
import com.tylerholcombe.leagueit.league.data.league.rating.RatingStrategy;

public class LeagueDto {
    private Long leagueId;
    private String leagueName;
    private Integer teamSize;
    private RatingStrategy ratingStrategy;
    private Long ownerId;
    private String ownerUsername;

    public LeagueDto() {
    }

    public LeagueDto(League league) {
        this.leagueId = league.getLeagueId();
        this.leagueName = league.getLeagueName();
        this.teamSize = league.getTeamSize();
        this.ratingStrategy = league.getRatingStrategy();
        this.ownerId = league.getOwnerId();
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
}
