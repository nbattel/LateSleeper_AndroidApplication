package com.twelve.latesleeper.user;

import com.twelve.latesleeper.model.Journal;
import com.twelve.latesleeper.model.User;

public class CurrentUser {

    private static CurrentUser currentUser;

    public static Journal journal;
    public static User user;

    private static String ID;

    static{
        currentUser = new CurrentUser();
    }

    public CurrentUser(){ }

//    public static void setUser(User newUser){
//        user = newUser;
//        journal = Database.getJournal(ID);
//    }

    public static void setJournal(Journal newJournal){
        journal = newJournal;
    }

    public static Journal getJournal(){
        return journal;
    }

    public static void setID(String id){
        ID = id;
    }

    public static String getID(){
        return ID;
    }

}
