package com.example.mateus.mapaedes.helpers;

import com.orm.SugarRecord;

/**
 * Created by zazah on 12/10/2017.
 */

public class LoggedUser extends SugarRecord<LoggedUser> {
    public User user;

    public LoggedUser(User user) {
        this.user = user;
    }

    public LoggedUser() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

