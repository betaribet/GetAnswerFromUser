package com.example.getanswerfromuser;



public class QuestionLibrary {

    private String mQuestions [] = {
            "What is your name?",
            "What is your birth year?",
            "Where did you born?",
            "Where are you from?"

    };

    private String mContent []={
            "name",
            "year",
            "city",
            "city"
    } ;

    private String mCorrectAnswers[] = {"Alice", "2010", "Bitlis", "Sivas"};




    public String getQuestion(int a) {
        String question = mQuestions[a];
        return question;
    }


    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }

    public String getmContent(int a) {
        String content = mContent[a];
        return content;
    }

}
