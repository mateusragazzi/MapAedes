package com.example.mateus.mapaedes.helpers;

import com.orm.SugarRecord;

/**
 * Created by zazah on 19/10/2017.
 */

public class Disease extends SugarRecord<Disease> {
    public long registerID;
    public String disease;
    public String nameUser;
    public String address;
    public String date;
    public Double lat;
    public Double lng;


    public Disease() {
    }

    public Disease(long registerID, String disease, String nameUser, String address, String date, Double lat, Double lng) {
        this.registerID = registerID;
        this.disease = disease;
        this.nameUser = nameUser;
        this.address = address;
        this.date = date;
        this.lat = lat;
        this.lng = lng;
    }

    public long getRegisterID() {
        return registerID;
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

    public String getDate() {
        return date;
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
