package com.twelve.latesleeper.activity;

public class QuestionLibrary {
    public static String mcQuestions[] = {
                    "This pattern causes me to have a lack of sleep",
                    "This pattern causes me to not get my work done",
                    "This pattern causes me to get nervous/upset",
                    "This pattern causes me to procrastinate"

    };

    private String mcChoices[] = {"0 (Never)", "1 (Rarely)", "2 (Sometimes)","3 (Often)", "4 (Always)"};

    public String getQuestion(int a) {
        return mcQuestions[a];
    }

    public String getChoice(int a) {
        return mcChoices[a];
    }
}
