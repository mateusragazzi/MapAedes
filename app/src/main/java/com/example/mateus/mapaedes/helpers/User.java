package com.example.mateus.mapaedes.helpers;

import com.orm.SugarRecord;

/**
 * Created by zazah on 19/10/2017.
 */

public class User extends SugarRecord<User> {
    public String name;
    public String email;
    public String password;
    public String city;
    public Double lat;
    public Double lng;
    public int type;

    public User() {}

    public User(String name, String email, String password, String city, Double lat, Double lng, int type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.city = city;
        this.lat = lat;
        this.lng = lng;
        this.type = type;
    }

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
