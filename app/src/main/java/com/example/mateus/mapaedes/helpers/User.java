package com.example.mateus.mapaedes.helpers;

import com.orm.SugarRecord;

public class User extends SugarRecord {
    private int userID;
    private String name;
    private String email;
    private String password;
    private String city;
    private Double lat;
    private Double lng;
    /* 1 for doctors
     * 2 for Agent
     * 3 for Population
     * 4 for administrator
     */
    private int type;

    public User() {}

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
