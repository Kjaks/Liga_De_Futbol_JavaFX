package com.karolis.futbolfx;

/**
 *
 * @author kjaks
 */
public class Team {
    private final String name;
    private final int matchesPlayed, matchesWon, matchesDrawn, matchesLost, points;

    public Team(String name, int matchesPlayed, int matchesWon, int matchesDrawn, int matchesLost, int points) {
        this.name = name;
        this.matchesPlayed = matchesPlayed;
        this.matchesWon = matchesWon;
        this.matchesDrawn = matchesDrawn;
        this.matchesLost = matchesLost;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public int getMatchesWon() {
        return matchesWon;
    }

    public int getMatchesDrawn() {
        return matchesDrawn;
    }

    public int getMatchesLost() {
        return matchesLost;
    }

    public int getPoints() {
        return points;
    }
}
