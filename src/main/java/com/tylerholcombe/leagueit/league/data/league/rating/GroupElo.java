package com.tylerholcombe.leagueit.league.data.league.rating;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupElo implements RatingStrategyInterface {
    private static final double K = 50;

    @Override
    public Map.Entry<List<Double>, List<Double>> calculateNewRating(List<Double> winnerStartingRating, List<Double> loserStartingRating) {
        double averageWinnerElo = winnerStartingRating.stream().mapToDouble(value -> value.doubleValue()).average().getAsDouble();
        double averageLoserElo = loserStartingRating.stream().mapToDouble(value -> value.doubleValue()).average().getAsDouble();

        List<Double> updatedWinnerElos = new ArrayList<>();
        for (Double winner : winnerStartingRating) {
            Double updatedWinnerElo = winner + calculateEloDifference(winner, averageLoserElo, true);
            updatedWinnerElos.add(updatedWinnerElo);
        }
        List<Double> updatedLoserElos = new ArrayList<>();
        for (Double loser : loserStartingRating) {
            Double updatedLoserElo = loser + calculateEloDifference(loser, averageWinnerElo, true);
            updatedLoserElos.add(updatedLoserElo);
        }

        return new AbstractMap.SimpleEntry<>(updatedWinnerElos, updatedLoserElos);
    }

    private Double calculateEloDifference(Double playerElo, Double opponentElo, boolean isWin) {
        double rPlayer = Math.pow(10, (playerElo / 400));
        double rOpponent = Math.pow(10, (opponentElo / 400));

        double ePlayer = rPlayer / (rPlayer + rOpponent);

        double c = 0.0;
        if (isWin) {
            c = 1.0;
        }
        double playerEloDiff = K * (c - ePlayer);

        return playerEloDiff;
    }
}
