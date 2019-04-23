package com.example.getanswerfromuser;

public class Move {

    private String date;
    private String question;
    private String userAnswer;
    private boolean check;

    public Move(){

    }

    public Move(String date, String question, String userAnswer, boolean check){
        this.date = date;
        this.question = question;
        this.userAnswer = userAnswer;
        this.check = check;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer= userAnswer;
    }

    public boolean getCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check= check;
    }
}
