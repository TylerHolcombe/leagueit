package com.tylerholcombe.leagueit.league.rest;

import com.tylerholcombe.leagueit.league.data.Player;

public class PlayerDto {
    private Long playerId;
    private Double rating;
    private Integer wins;
    private Integer losses;
    private Integer ties;
    private Long userId;
    private String username;
    private Long leagueId;
    private String leagueName;

    public PlayerDto() {
    }

    public PlayerDto(Player player) {
        this.playerId = player.getPlayerId();
        this.rating = player.getRating();
        this.wins = player.getWins();
        this.losses = player.getLosses();
        this.ties = player.getTies();
        this.userId = player.getUser().getApplicationUserId();
        this.username = player.getUser().getUsername();
        this.leagueId = player.getLeague().getLeagueId();
        this.leagueName = player.getLeague().getLeagueName();
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public Integer getTies() {
        return ties;
    }

    public void setTies(Integer ties) {
        this.ties = ties;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
