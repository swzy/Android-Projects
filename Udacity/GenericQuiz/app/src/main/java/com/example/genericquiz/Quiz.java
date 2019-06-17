package com.example.genericquiz;

public class Quiz {
    private String question;
    private int num;
    private boolean ans;

    public Quiz (int num, String question, boolean answer){
        this.num = num;
        this.question = question;
        this.ans = answer;
    }


    public String getQuestion() {
        return this.question;
    }
    public int getNum() {
        return this.num;
    }
    public boolean getAns() {
        return this.ans;
    }
}
