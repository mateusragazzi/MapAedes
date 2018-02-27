package com.example.mateus.mapaedes.helpers;

import com.orm.SugarRecord;

public class Disease extends SugarRecord {
    private long registerID;
    private String disease;
    private String nameUser;
    private String address;
    private String date;
    private Double lat;
    private Double lng;


    public Disease() {
    }

    public void setRegisterID(long registerID) {
        this.registerID = registerID;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate(String date) {
        this.date = date;
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
