package com.tylerholcombe.leagueit.league.data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class GameResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameResultId;
    private Double ratingBefore;
    private Boolean isWinner;
    private Double ratingAfter;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id", nullable = false)
    private Game game;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private Player player;

    public Long getGameResultId() {
        return gameResultId;
    }

    public void setGameResultId(Long gameResultId) {
        this.gameResultId = gameResultId;
    }

    public Double getRatingBefore() {
        return ratingBefore;
    }

    public void setRatingBefore(Double ratingBefore) {
        this.ratingBefore = ratingBefore;
    }

    public Boolean getWinner() {
        return isWinner;
    }

    public void setWinner(Boolean winner) {
        isWinner = winner;
    }

    public Double getRatingAfter() {
        return ratingAfter;
    }

    public void setRatingAfter(Double ratingAfter) {
        this.ratingAfter = ratingAfter;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
