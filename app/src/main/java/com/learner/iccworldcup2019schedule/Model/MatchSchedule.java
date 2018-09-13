package com.learner.iccworldcup2019schedule.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatchSchedule {

    private String team1;

    private String team2;

    private String date;

    private String time;

    private String stadium;

    private String team1_flag_url;

    private String team2_flag_url;

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getTeam1_flag_url() {
        return team1_flag_url;
    }

    public void setTeam1_flag_url(String team1_flag_url) {
        this.team1_flag_url = team1_flag_url;
    }

    public String getTeam2_flag_url() {
        return team2_flag_url;
    }

    public void setTeam2_flag_url(String team2_flag_url) {
        this.team2_flag_url = team2_flag_url;
    }
}
