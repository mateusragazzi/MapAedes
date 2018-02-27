package com.example.mateus.mapaedes.helpers;

import com.orm.SugarRecord;


public class LoggedUser extends SugarRecord {
    private User user;

    public LoggedUser() {
    }

    public LoggedUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}

