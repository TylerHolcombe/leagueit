package com.tylerholcombe.leagueit.league.data.league;

import com.tylerholcombe.leagueit.league.data.Game;
import com.tylerholcombe.leagueit.league.data.Player;
import com.tylerholcombe.leagueit.league.data.league.rating.RatingStrategy;
import com.tylerholcombe.leagueit.user.data.ApplicationUser;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leagueId;
    private String leagueName;
    private Integer teamSize;
    private RatingStrategy ratingStrategy;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private ApplicationUser owner;
    @OneToMany(mappedBy = "league")
    private Set<Game> games;
    @OneToMany(mappedBy = "league")
    private Set<Player> players;

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

    public ApplicationUser getOwner() {
        return owner;
    }

    public void setOwner(ApplicationUser owner) {
        this.owner = owner;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}
