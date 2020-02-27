package com.twelve.latesleeper.model;

import java.util.ArrayList;

public class Journal {

    private ArrayList<Entry> journal;

    public void Journal(){
        journal = new ArrayList<Entry>();
    }

    public void addEntry(Entry entry){
        journal.add(entry);
    }

}
