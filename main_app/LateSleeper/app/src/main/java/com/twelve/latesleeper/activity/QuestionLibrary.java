package com.twelve.latesleeper.activity;

public class QuestionLibrary {
    private String mcQuestions[] = {
                    "This pattern causes me to have a lack of sleep.",
                    "This pattern causes me to not get my work done",
                    "This pattern causes me to get nervous/upset.",
                    "This pattern causes me to procrastinate"

    };

    private String mcChoices[][] = {
            {"0 (Never)", "1 (rarely)", "2 (Sometimes)","3 (Often)", "4 (Always)"},
            {"0 (Never)", "1 (rarely)", "2 (Sometimes)","3 (Often)", "4 (Always)"},
            {"0 (Never)", "1 (rarely)", "2 (Sometimes)","3 (Often)", "4 (Always)"},
            {"0 (Never)", "1 (rarely)", "2 (Sometimes)","3 (Often)", "4 (Always)"}
    };

    public String getQuestion(int a) {
        String question = mcQuestions[a];
        return question;
    }


    public String getChoice1(int a) {
        String choice0 = mcChoices[a][0];
        return choice0;
    }


    public String getChoice2(int a) {
        String choice1 = mcChoices[a][1];
        return choice1;
    }

    public String getChoice3(int a) {
        String choice2 = mcChoices[a][2];
        return choice2;
    }
    public String getChoice4(int a) {
        String choice3 = mcChoices[a][3];
        return choice3;
    }
    public String getChoice5(int a) {
        String choice4 = mcChoices[a][4];
        return choice4;
    }

}
