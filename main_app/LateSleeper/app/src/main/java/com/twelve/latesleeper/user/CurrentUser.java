package com.twelve.latesleeper.user;

import com.twelve.latesleeper.model.Journal;
import com.twelve.latesleeper.model.User;

public class CurrentUser {

    private static CurrentUser currentUser;
    private Journal journal;


    public CurrentUser(User user, Journal journal){

    }

    public static CurrentUser getCurrentUser(){
        return currentUser;
    }

}
