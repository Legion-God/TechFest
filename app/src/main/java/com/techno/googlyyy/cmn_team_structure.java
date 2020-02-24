package com.techno.googlyyy;

public class cmn_team_structure {

    String team_name;
    Integer team_num;

    public cmn_team_structure(){

    }


    public cmn_team_structure(String team_name, Integer team_num) {
        this.team_name = team_name;
        this.team_num = team_num;
    }


    public  String getTeam_name() {
        return team_name;
    }

    public Integer getTeam_num() {
        return team_num;
    }
}
