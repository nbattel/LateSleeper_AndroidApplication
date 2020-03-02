package com.twelve.latesleeper.activity;

public class Flag {

    private static boolean isGoalComplete = false;

    public static void toggleIsGoalComplete(){
        isGoalComplete = !isGoalComplete;
    }

    public static boolean getIsGoalComplete(){
        return isGoalComplete;
    }
}
