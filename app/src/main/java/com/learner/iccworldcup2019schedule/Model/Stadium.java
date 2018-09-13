package com.learner.iccworldcup2019schedule.Model;

import java.io.Serializable;

public class Stadium implements Serializable {

    private String city;

    private String ground;

    private String home_ground;

    private String capacity;

    private String matches;

    private String established;

    private String floodLight;

    private String endNames;

    private String imageUrl;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGround() {
        return ground;
    }

    public void setGround(String ground) {
        this.ground = ground;
    }

    public String getHome_ground() {
        return home_ground;
    }

    public void setHome_ground(String home_ground) {
        this.home_ground = home_ground;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getMatches() {
        return matches;
    }

    public void setMatches(String matches) {
        this.matches = matches;
    }

    public String getEstablished() {
        return established;
    }

    public void setEstablished(String established) {
        this.established = established;
    }

    public String getFloodLight() {
        return floodLight;
    }

    public void setFloodLight(String floodLight) {
        this.floodLight = floodLight;
    }

    public String getEndNames() {
        return endNames;
    }

    public void setEndNames(String endNames) {
        this.endNames = endNames;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
