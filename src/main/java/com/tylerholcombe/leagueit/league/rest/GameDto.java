package com.tylerholcombe.leagueit.league.rest;

import java.util.List;

public class GameDto {
    private Long gameId;
    private Long leagueId;
    private List<Long> winnerIds;
    private List<Long> loserIds;

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }

    public List<Long> getWinnerIds() {
        return winnerIds;
    }

    public void setWinnerIds(List<Long> winnerIds) {
        this.winnerIds = winnerIds;
    }

    public List<Long> getLoserIds() {
        return loserIds;
    }

    public void setLoserIds(List<Long> loserIds) {
        this.loserIds = loserIds;
    }
}
