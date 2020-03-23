package com.twelve.latesleeper.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Entry implements Serializable {
    HashMap<String, String> entry;

    public Entry(String body,String title,String date)
    {
        entry = new HashMap<>();

        entry.put("body", body);
        entry.put("title", title);
        entry.put("date", date);
    }

    public Entry(){
        entry = new HashMap<>();

        entry.put("body", "");
        entry.put("title", "");
        entry.put("date", "");
    }

    public HashMap<String, String> getEntry() { return entry; }

    public String getBody(){
        return entry.get("body");
    }

    public String getTitle(){
        return entry.get("title");
    }

    public String getDate(){
        return entry.get("date");
    }

    public void setBody(String body){
        entry.replace("body", body);
    }

    public void setTitle(String title){
        entry.replace("body", title);
    }

    public void setDate(String date){
        entry.replace("body", date);
    }
}
