package com.twelve.latesleeper.model;

public class Entry {
    String body;
    String title;
    String date;

    public Entry(String body,String title,String date)
    {
        this.body = body;
        this.title = title;
        this.date = date;
    }

    public String getBody() {return this.body; }
    public String getTitle(){return this.title;}
    public  String getDate(){return this.date;}
    public void setBody(String body){this.body = body;}
    public  void setTitle(String title){this.title = title;}
    public  void setDate(String date){this.date = date;}
}
