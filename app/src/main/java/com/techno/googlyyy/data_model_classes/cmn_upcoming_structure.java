package com.techno.googlyyy.data_model_classes;

public class cmn_upcoming_structure {

    Integer round;
    Integer group;
    String team1;
    String team2;
    String date;
    String time;

    public cmn_upcoming_structure() {
    }

    public cmn_upcoming_structure(Integer round, Integer group, String team1, String team2, String date, String time) {
        this.round = round;
        this.group = group;
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
        this.time = time;
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

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
