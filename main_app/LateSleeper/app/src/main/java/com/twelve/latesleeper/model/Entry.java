package com.twelve.latesleeper.model;

import java.util.HashMap;
import java.util.Map;

public class Entry {
    Map<String, Object> entry = new HashMap<>();

    public Entry(String body,String title,String date)
    {
        entry.put("body", body);
        entry.put("title", title);
        entry.put("date", date);
    }

    public Map getEntry() { return entry; }
//    public String getBody() {return this.body; }
//    public String getTitle(){return this.title;}
//    public  String getDate(){return this.date;}
//    public void setBody(String body){this.body = body;}
//    public  void setTitle(String title){this.title = title;}
//    public  void setDate(String date){this.date = date;}
}
