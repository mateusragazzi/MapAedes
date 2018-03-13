package com.example.mateus.mapaedes.helpers;

import com.orm.SugarRecord;

import java.util.Date;

public class Disease extends SugarRecord {
    private String type;
    private String disease;
    private String address;
    private Double lat;
    private Double lng;
    private Date date;
    private int active;
    private int userID;

    public Disease() {}

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public int getActive() { return active; }

    public void setActive(int active) { this.active = active; }

    public int getUserID() { return userID; }

    public void setUserID(int userID) { this.userID = userID; }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
