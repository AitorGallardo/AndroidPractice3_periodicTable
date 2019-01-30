package com.example.aitor.androidpractice1_periodictable;

import java.util.ArrayList;

public class Quiz {

    private String question;
    private ArrayList<Answer> answers ;
    private boolean response;

    public Quiz(String question, ArrayList<Answer> answers, boolean response){
        this.question = question;
        this.answers = answers;
        this.response = response;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    // get-setters
}

 class Answer {

    private String name;
    private boolean value;

    public Answer(String name, boolean value){
            this.name = name;
            this.value = value;
            }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public boolean isValue() {
         return value;
     }

     public void setValue(boolean value) {
         this.value = value;
     }
 }
