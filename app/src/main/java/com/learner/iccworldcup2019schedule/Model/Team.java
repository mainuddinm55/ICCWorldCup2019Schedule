package com.learner.iccworldcup2019schedule.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Team implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("coach")
    @Expose
    private String coach;
    @SerializedName("iccRanking")
    @Expose
    private String iccRanking;
    @SerializedName("worldCupPerticipant")
    @Expose
    private String worldCupPerticipant;
    @SerializedName("bestPerformance")
    @Expose
    private String bestPerformance;
    @SerializedName("nickName")
    @Expose
    private String nickName;
    @SerializedName("sponsor")
    @Expose
    private String sponsor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getIccRanking() {
        return iccRanking;
    }

    public void setIccRanking(String iccRanking) {
        this.iccRanking = iccRanking;
    }

    public String getWorldCupPerticipant() {
        return worldCupPerticipant;
    }

    public void setWorldCupPerticipant(String worldCupPerticipant) {
        this.worldCupPerticipant = worldCupPerticipant;
    }

    public String getBestPerformance() {
        return bestPerformance;
    }

    public void setBestPerformance(String bestPerformance) {
        this.bestPerformance = bestPerformance;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }
}