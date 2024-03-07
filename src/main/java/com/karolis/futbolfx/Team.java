package com.karolis.futbolfx;

/**
 * Represents a football team with its attributes such as name, matches played, matches won, matches drawn,
 * matches lost, and points.
 * @author kjaks
 */
public class Team {
    private final String name;
    private final int matchesPlayed, matchesWon, matchesDrawn, matchesLost, points;

    /**
     * Constructs a new Team object with the specified attributes.
     * @param name The name of the team.
     * @param matchesPlayed The number of matches played by the team.
     * @param matchesWon The number of matches won by the team.
     * @param matchesDrawn The number of matches drawn by the team.
     * @param matchesLost The number of matches lost by the team.
     * @param points The total points accumulated by the team.
     */
    public Team(String name, int matchesPlayed, int matchesWon, int matchesDrawn, int matchesLost, int points) {
        this.name = name;
        this.matchesPlayed = matchesPlayed;
        this.matchesWon = matchesWon;
        this.matchesDrawn = matchesDrawn;
        this.matchesLost = matchesLost;
        this.points = points;
    }

    /**
     * Returns the name of the team.
     * @return The name of the team.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the number of matches played by the team.
     * @return The number of matches played by the team.
     */
    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    /**
     * Returns the number of matches won by the team.
     * @return The number of matches won by the team.
     */
    public int getMatchesWon() {
        return matchesWon;
    }

    /**
     * Returns the number of matches drawn by the team.
     * @return The number of matches drawn by the team.
     */
    public int getMatchesDrawn() {
        return matchesDrawn;
    }

    /**
     * Returns the number of matches lost by the team.
     * @return The number of matches lost by the team.
     */
    public int getMatchesLost() {
        return matchesLost;
    }

    /**
     * Returns the total points accumulated by the team.
     * @return The total points accumulated by the team.
     */
    public int getPoints() {
        return points;
    }
}
