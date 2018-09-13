package com.learner.iccworldcup2019schedule.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats {

    @SerializedName("totalPlayedMatchs")
    @Expose
    private String totalPlayedMatchs;
    @SerializedName("totalMatchs")
    @Expose
    private String totalMatchs;
    @SerializedName("totalRuns")
    @Expose
    private String totalRuns;
    @SerializedName("averageRunPerMatch")
    @Expose
    private String averageRunPerMatch;
    @SerializedName("totalWickets")
    @Expose
    private String totalWickets;
    @SerializedName("averageWicketPerMatch")
    @Expose
    private String averageWicketPerMatch;
    @SerializedName("totalSixes")
    @Expose
    private String totalSixes;
    @SerializedName("averageSixPerMatch")
    @Expose
    private String averageSixPerMatch;
    @SerializedName("totalFours")
    @Expose
    private String totalFours;
    @SerializedName("averageFourPerMatch")
    @Expose
    private String averageFourPerMatch;
    @SerializedName("totalCentury")
    @Expose
    private String totalCentury;
    @SerializedName("averageCenturyPerMatch")
    @Expose
    private String averageCenturyPerMatch;

    public String getTotalPlayedMatchs() {
        return totalPlayedMatchs;
    }

    public void setTotalPlayedMatchs(String totalPlayedMatchs) {
        this.totalPlayedMatchs = totalPlayedMatchs;
    }

    public String getTotalMatchs() {
        return totalMatchs;
    }

    public void setTotalMatchs(String totalMatchs) {
        this.totalMatchs = totalMatchs;
    }

    public String getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(String totalRuns) {
        this.totalRuns = totalRuns;
    }

    public String getAverageRunPerMatch() {
        return averageRunPerMatch;
    }

    public void setAverageRunPerMatch(String averageRunPerMatch) {
        this.averageRunPerMatch = averageRunPerMatch;
    }

    public String getTotalWickets() {
        return totalWickets;
    }

    public void setTotalWickets(String totalWickets) {
        this.totalWickets = totalWickets;
    }

    public String getAverageWicketPerMatch() {
        return averageWicketPerMatch;
    }

    public void setAverageWicketPerMatch(String averageWicketPerMatch) {
        this.averageWicketPerMatch = averageWicketPerMatch;
    }

    public String getTotalSixes() {
        return totalSixes;
    }

    public void setTotalSixes(String totalSixes) {
        this.totalSixes = totalSixes;
    }

    public String getAverageSixPerMatch() {
        return averageSixPerMatch;
    }

    public void setAverageSixPerMatch(String averageSixPerMatch) {
        this.averageSixPerMatch = averageSixPerMatch;
    }

    public String getTotalFours() {
        return totalFours;
    }

    public void setTotalFours(String totalFours) {
        this.totalFours = totalFours;
    }

    public String getAverageFourPerMatch() {
        return averageFourPerMatch;
    }

    public void setAverageFourPerMatch(String averageFourPerMatch) {
        this.averageFourPerMatch = averageFourPerMatch;
    }

    public String getTotalCentury() {
        return totalCentury;
    }

    public void setTotalCentury(String totalCentury) {
        this.totalCentury = totalCentury;
    }

    public String getAverageCenturyPerMatch() {
        return averageCenturyPerMatch;
    }

    public void setAverageCenturyPerMatch(String averageCenturyPerMatch) {
        this.averageCenturyPerMatch = averageCenturyPerMatch;
    }

}