package com.twelve.latesleeper.model;

import java.util.HashMap;

public class Goal {

    HashMap<String, Object> goalInfo;

    public Goal()
    {
        goalInfo = new HashMap<>();

        goalInfo.put("sleepTime", "00:00");
        goalInfo.put("days", 7);
        goalInfo.put("daysCompleted", 0);
        goalInfo.put("totalHours", 0);
    }

    public Goal(String time, Integer days, Integer daysCompleted, Integer totalHours)
    {
        goalInfo = new HashMap<>();

        goalInfo.put("sleepTime", time);
        goalInfo.put("days", days);
        goalInfo.put("daysCompleted", daysCompleted);
        goalInfo.put("totalHours", totalHours);
    }

    public HashMap<String, Object> getGoal() { return goalInfo; }
}
