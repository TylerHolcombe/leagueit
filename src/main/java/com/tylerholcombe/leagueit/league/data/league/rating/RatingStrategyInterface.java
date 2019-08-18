package com.tylerholcombe.leagueit.league.data.league.rating;

import java.util.List;
import java.util.Map;

public interface RatingStrategyInterface {
    /**
     * Takes in current ratings for a list of winners and a list of losers and calculates their new ratings.
     *
     * @param winnerStartingRating Ratings for the winning team prior to winning
     * @param loserStartingRating  Ratings for the losing team prior to losing
     * @return a pair of lists. The first list contains the winners new ratings after winning, in the same respective order as their starting ratings.
     * The second list contains the losers new ratings after losing, in the same respective order as their starting ratings.
     */
    Map.Entry<List<Double>, List<Double>> calculateNewRating(List<Double> winnerStartingRating, List<Double> loserStartingRating);
}
