package com.techno.googlyyy.data_model_classes;

public class cmn_live_matches_structure {
    Integer round;
    Integer group;
    String team1;
    String team2;
    Integer team1score;
    Integer team2score;
    String Winner;

    public cmn_live_matches_structure(Integer round, Integer group, String team1, String team2, Integer team1score, Integer team2score, String winner) {
        this.round = round;
        this.group = group;
        this.team1 = team1;
        this.team2 = team2;
        this.team1score = team1score;
        this.team2score = team2score;
        Winner = winner;
    }

    public cmn_live_matches_structure() {
    }

    public Integer getRound() {
        return round;
    }

    public Integer getGroup() {
        return group;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public Integer getTeam1score() {
        return team1score;
    }

    public Integer getTeam2score() {
        return team2score;
    }


    public String getWinner() {
        return Winner;
    }
}
